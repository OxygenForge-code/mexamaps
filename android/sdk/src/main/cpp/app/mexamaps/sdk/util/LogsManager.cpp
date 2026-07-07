#include <jni.h>
#include "app/mexamaps/sdk/core/logging.hpp"

extern "C"
{
JNIEXPORT void Java_app_mexamaps_sdk_util_log_LogsManager_nativeToggleCoreDebugLogs(JNIEnv * /*env*/,
                                                                                       jclass /*clazz*/,
                                                                                       jboolean enabled)
{
  jni::ToggleDebugLogs(enabled);
}
}  // extern "C"
