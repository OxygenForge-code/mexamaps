package app.mexamaps.car;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.Screen;
import androidx.car.app.SessionInfo;
import androidx.lifecycle.LifecycleOwner;
import app.mexamaps.MwmApplication;
import app.mexamaps.R;
import app.mexamaps.car.screens.ErrorScreen;
import app.mexamaps.car.screens.MapPlaceholderScreen;
import app.mexamaps.car.screens.MapScreen;
import app.mexamaps.car.screens.download.DownloadMapsScreenBuilder;
import app.mexamaps.car.screens.download.DownloaderHelpers;
import app.mexamaps.car.screens.permissions.RequestPermissionsScreenBuilder;
import app.mexamaps.car.util.IntentUtils;
import app.mexamaps.car.util.UserActionRequired;
import app.mexamaps.sdk.MexaMaps;
import app.mexamaps.sdk.display.DisplayChangedListener;
import app.mexamaps.sdk.display.DisplayType;
import app.mexamaps.sdk.location.LocationUtils;
import app.mexamaps.sdk.util.Assert;
import app.mexamaps.sdk.util.log.Logger;
import java.util.ArrayList;
import java.util.List;

public final class AndroidAutoSession extends CarAppSessionBase implements DisplayChangedListener
{
  private static final String TAG = AndroidAutoSession.class.getSimpleName();

  private final boolean mInitFailed;

  public AndroidAutoSession(@NonNull MexaMaps organicMapsContext, @Nullable SessionInfo sessionInfo, boolean isDebug,
                            boolean initFailed)
  {
    super(organicMapsContext, sessionInfo, isDebug);
    mInitFailed = initFailed;
  }

  @Override
  public void onNewIntent(@NonNull Intent intent)
  {
    Logger.d(TAG, intent.toString());
    Assert.debug(mDisplayManager != null, "mDisplayManager is null");
    IntentUtils.processIntent(getCarContext(), mMexaMapsContext, mSurfaceRenderer, mDisplayManager, intent);
  }

  @Override
  public void onCreate(@NonNull LifecycleOwner owner)
  {
    super.onCreate(owner);
    mDisplayManager = MwmApplication.from(getCarContext()).getDisplayManager();
    mDisplayManager.addListener(DisplayType.Car, this);
  }

  @Override
  public void onDestroy(@NonNull LifecycleOwner owner)
  {
    super.onDestroy(owner);
    Assert.debug(mDisplayManager != null, "mDisplayManager is null");
    mDisplayManager.removeListener(DisplayType.Car);
  }

  @NonNull
  protected Screen prepareScreens()
  {
    if (mInitFailed)
      return new ErrorScreen.Builder(getCarContext(), mMexaMapsContext)
          .setErrorMessage(R.string.dialog_error_storage_message)
          .build();

    final List<Screen> screensStack = new ArrayList<>();
    screensStack.add(new MapScreen(getCarContext(), mMexaMapsContext, mSurfaceRenderer));

    if (DownloaderHelpers.isWorldMapsDownloadNeeded(mMexaMapsContext.getFlavor()))
    {
      mScreenManager.push(new DownloadMapsScreenBuilder(getCarContext(), mMexaMapsContext)
                              .setDownloaderType(DownloadMapsScreenBuilder.DownloaderType.FirstLaunch)
                              .build());
    }

    if (!LocationUtils.checkFineLocationPermission(getCarContext()))
      screensStack.add(
          RequestPermissionsScreenBuilder.build(getCarContext(), mMexaMapsContext, mSensorsManager::onStart));

    Assert.debug(mDisplayManager != null, "mDisplayManager is null");
    if (mDisplayManager.isDeviceDisplayUsed())
    {
      mSurfaceRenderer.disable();
      onStop(this);
      screensStack.add(new MapPlaceholderScreen(getCarContext(), mMexaMapsContext));
    }

    for (int i = 0; i < screensStack.size() - 1; i++)
      mScreenManager.push(screensStack.get(i));

    return screensStack.get(screensStack.size() - 1);
  }

  @Override
  public void onDisplayChangedToDevice(@NonNull Runnable onTaskFinishedCallback)
  {
    Logger.d(TAG);
    final Screen topScreen = mScreenManager.getTop();
    onStop(this);
    mSurfaceRenderer.disable();

    final MapPlaceholderScreen mapPlaceholderScreen = new MapPlaceholderScreen(getCarContext(), mMexaMapsContext);
    if (topScreen instanceof UserActionRequired)
      mScreenManager.popToRoot();

    mScreenManager.push(mapPlaceholderScreen);

    onTaskFinishedCallback.run();
  }

  @Override
  public void onDisplayChangedToCar(@NonNull Runnable onTaskFinishedCallback)
  {
    Logger.d(TAG);
    onStart(this);
    mSurfaceRenderer.enable();

    if (mScreenManager.getTop() instanceof MapPlaceholderScreen)
      mScreenManager.pop();

    onTaskFinishedCallback.run();
  }

  @Override
  protected boolean isCarScreenUsed()
  {
    Assert.debug(mDisplayManager != null, "mDisplayManager is null");
    return mDisplayManager.isCarDisplayUsed();
  }
}
