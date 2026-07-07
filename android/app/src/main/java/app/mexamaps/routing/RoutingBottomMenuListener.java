package app.mexamaps.routing;

import androidx.annotation.NonNull;
import app.mexamaps.sdk.routing.RouteMarkType;

public interface RoutingBottomMenuListener
{
  void onUseMyPositionAsStart();
  void onSearchRoutePoint(@NonNull RouteMarkType type);
  void onRoutingStart();
}
