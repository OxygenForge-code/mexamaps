package app.mexamaps.sdk.bookmarks.data;

import androidx.annotation.Keep;
import app.mexamaps.sdk.util.Distance;

// Used by JNI.
@Keep
@SuppressWarnings("unused")
public class DistanceAndAzimut
{
  private final Distance mDistance;
  private final double mAzimuth;

  public Distance getDistance()
  {
    return mDistance;
  }

  public double getAzimuth()
  {
    return mAzimuth;
  }

  public DistanceAndAzimut(Distance distance, double azimuth)
  {
    mDistance = distance;
    mAzimuth = azimuth;
  }
}
