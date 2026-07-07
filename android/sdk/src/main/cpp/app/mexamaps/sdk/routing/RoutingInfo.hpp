#pragma once

#include "app/mexamaps/sdk/core/jni_helper.hpp"
#include "app/mexamaps/sdk/routing/CarDirection.hpp"
#include "app/mexamaps/sdk/routing/LaneInfo.hpp"
#include "app/mexamaps/sdk/routing/PedestrianDirection.hpp"
#include "app/mexamaps/sdk/routing/roadshield/RoadShieldInfo.hpp"

#include "map/routing_manager.hpp"

jobject CreateRoutingInfo(JNIEnv * env, routing::FollowingInfo const & info, RoutingManager & rm)
{
  static jclass const klass = jni::GetGlobalClassRef(env, "app/mexamaps/sdk/routing/RoutingInfo");
  // clang-format off
  static jmethodID const ctorRouteInfoID = jni::GetConstructorID(env, klass,
    "("
    "Lapp/mexamaps/sdk/util/Distance;"                      // distToTarget
    "Lapp/mexamaps/sdk/util/Distance;"                      // distToTurn
    "Ljava/lang/String;"                                       // currentStreet
    "Ljava/lang/String;"                                       // nextStreet
    "Lapp/mexamaps/sdk/routing/roadshield/RoadShieldInfo;"  // nextStreetRoadShields
    "Ljava/lang/String;"                                       // nextNextStreet
    "Lapp/mexamaps/sdk/routing/roadshield/RoadShieldInfo;"  // nextNextStreetRoadShields
    "D"                                                        // completionPercent
    "Lapp/mexamaps/sdk/routing/CarDirection;"               // carTurnDirection
    "Lapp/mexamaps/sdk/routing/CarDirection;"               // carNextTurnDirection
    "Lapp/mexamaps/sdk/routing/PedestrianDirection;"        // pedestrianDirection
    "I"                                                        // exitNum
    "I"                                                        // totalTime
    "[Lapp/mexamaps/sdk/routing/LaneInfo;"                  // lanes
    "D"                                                        // speedLimitMps
    "Z"                                                        // speedLimitExceeded
    "Z"                                                        // shouldPlayWarningSignal
    ")V"
  );
  // clang-format on

  // clang-format off
  jobject const result = env->NewObject(klass, ctorRouteInfoID,
    ToJavaDistance(env, info.m_distToTarget),
    ToJavaDistance(env, info.m_distToTurn),
    jni::ToJavaString(env, info.m_currentStreetName),
    jni::ToJavaString(env, info.m_nextStreetName),
    ToJavaRoadShieldInfo(env, info.m_nextStreetShields),
    jni::ToJavaString(env, info.m_nextNextStreetName),
    ToJavaRoadShieldInfo(env, info.m_nextNextStreetShields),
    info.m_completionPercent,
    ToJavaCarDirection(env, info.m_turn),
    ToJavaCarDirection(env, info.m_nextTurn),
    ToJavaPedestrianDirection(env, info.m_pedestrianTurn),
    info.m_exitNum,
    info.m_time,
    CreateLanesInfo(env, info.m_lanes),
    info.m_speedLimitMps,
    static_cast<jboolean>(rm.IsSpeedCamLimitExceeded()),
    static_cast<jboolean>(rm.GetSpeedCamManager().ShouldPlayBeepSignal())
  );
  // clang-format on
  ASSERT(result, (jni::DescribeException()));
  return result;
}
