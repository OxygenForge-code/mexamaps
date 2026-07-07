package app.mexamaps.sdk.car.renderer;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import androidx.lifecycle.LifecycleOwner;
import app.mexamaps.sdk.display.DisplayManager;
import app.mexamaps.sdk.location.LocationHelper;

public final class RendererFactory
{
  @NonNull
  public static Renderer create(@NonNull CarContext carContext, @NonNull DisplayManager displayManager,
                                @NonNull LocationHelper locationHelper, @NonNull LifecycleOwner lifecycleOwner)
  {
    if (android.os.Build.VERSION.SDK_INT >= 23)
      return new SurfaceRenderer(carContext, displayManager, locationHelper, lifecycleOwner);
    else
      return new SurfaceRendererLegacy(carContext, displayManager, locationHelper, lifecycleOwner);
  }
}
