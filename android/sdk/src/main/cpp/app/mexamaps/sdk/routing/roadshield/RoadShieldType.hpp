#pragma once

#include "app/mexamaps/sdk/core/jni_helper.hpp"

#include "indexer/road_shields_parser.hpp"

jobject ToJavaRoadShieldType(JNIEnv * env, ftypes::RoadShieldType roadShieldType);
