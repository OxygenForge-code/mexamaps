package app.mexamaps.car.util;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.car.app.CarContext;
import androidx.car.app.ScreenManager;
import app.mexamaps.car.screens.download.DownloadMapsScreen;
import app.mexamaps.car.screens.download.DownloadMapsScreenBuilder;
import app.mexamaps.sdk.MexaMaps;
import app.mexamaps.sdk.downloader.CountryItem;
import app.mexamaps.sdk.downloader.MapManager;
import app.mexamaps.sdk.routing.RoutingController;

public class CurrentCountryChangedListener implements MapManager.CurrentCountryChangedListener
{
  @Nullable
  private CarContext mCarContext;
  @Nullable
  private MexaMaps mMexaMapsContext;

  @Nullable
  private String mPreviousCountryId;

  @Override
  public void onCurrentCountryChanged(@Nullable String countryId)
  {
    if (TextUtils.isEmpty(countryId))
    {
      mPreviousCountryId = countryId;
      return;
    }

    if (mPreviousCountryId != null && mPreviousCountryId.equals(countryId))
      return;

    if (mCarContext == null || mMexaMapsContext == null)
      return;

    final ScreenManager screenManager = mCarContext.getCarService(ScreenManager.class);

    if (DownloadMapsScreen.MARKER.equals(screenManager.getTop().getMarker()))
      return;

    if (CountryItem.fill(countryId).present || RoutingController.get().isNavigating())
      return;

    mPreviousCountryId = countryId;
    screenManager.push(new DownloadMapsScreenBuilder(mCarContext, mMexaMapsContext)
                           .setDownloaderType(DownloadMapsScreenBuilder.DownloaderType.View)
                           .setMissingMaps(new String[] {countryId})
                           .build());
  }

  public void onStart(@NonNull final CarContext carContext, @NonNull MexaMaps organicMapsContext)
  {
    mCarContext = carContext;
    mMexaMapsContext = organicMapsContext;
    MapManager.nativeSubscribeOnCountryChanged(this);
  }

  public void onStop()
  {
    MapManager.nativeUnsubscribeOnCountryChanged();
    mMexaMapsContext = null;
    mCarContext = null;
  }
}
