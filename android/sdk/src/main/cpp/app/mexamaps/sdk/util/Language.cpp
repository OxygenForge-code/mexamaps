#include "android/sdk/src/main/cpp/app/mexamaps/sdk/core/jni_helper.hpp"
#include "platform/preferred_languages.hpp"

extern "C"
{
JNIEXPORT jstring Java_app_mexamaps_sdk_util_Language_nativeNormalize(JNIEnv * env, jclass type, jstring lang)
{
  std::string locale = languages::Normalize(jni::ToNativeString(env, lang));
  return jni::ToJavaString(env, locale);
}
}
