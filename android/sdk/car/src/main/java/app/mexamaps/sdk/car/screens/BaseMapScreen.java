package app.mexamaps.sdk.car.screens;

import androidx.annotation.NonNull;
import androidx.car.app.CarContext;
import app.mexamaps.sdk.MexaMaps;
import app.mexamaps.sdk.car.renderer.Renderer;

public abstract class BaseMapScreen extends BaseScreen
{
  @NonNull
  private final Renderer mSurfaceRenderer;

  public BaseMapScreen(@NonNull CarContext carContext, @NonNull MexaMaps organicMapsContext,
                       @NonNull Renderer surfaceRenderer)
  {
    super(carContext, organicMapsContext);
    mSurfaceRenderer = surfaceRenderer;
  }

  @NonNull
  protected Renderer getSurfaceRenderer()
  {
    return mSurfaceRenderer;
  }
}
