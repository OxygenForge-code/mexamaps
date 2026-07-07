#pragma once

#include "app/mexamaps/sdk/core/jni_helper.hpp"

#include "routing/following_info.hpp"

jobject ToJavaRoadShieldInfo(JNIEnv * env, routing::FollowingInfo::RoadShieldInfo const & roadShieldInfo);
