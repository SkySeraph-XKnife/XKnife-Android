//
// Created by SkySeraph on 2016/1/28.
//

#include <utils/logs.h>
#include <string>
#include "encrypt.h"

extern "C" {
JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *vm, void *reserved);

JNIEXPORT void JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_registerNatives(JNIEnv *env,
                                                                              jobject /* this */);

JNIEXPORT jstring JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_getSign(JNIEnv *env,
                                                                         jclass type,
                                                                         jobject context);

JNIEXPORT jboolean JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_verifySign(JNIEnv *env,
                                                                             jclass type,
                                                                             jobject context);

JNIEXPORT jstring JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_stringFromJNI(JNIEnv *env,
                                                                               jobject /* this */);
}

static JNINativeMethod gMethods[] = {
        {"verifySign", "()V", (void *) native_verifySign},
//        {"stringFromJNI", "(I)Ljava/lang/String;", (jstring *) stringFromJNI},
};

static int registerNativeMethods(JNIEnv *env, const char *className, JNINativeMethod *gMethods,
                                 int numMethods) {
    jclass clazz;
    LOGI("clazz is %s", className);
    clazz = env->FindClass(className);
    if (clazz == NULL) {
        LOGE("clazz is NULL");
        return JNI_FALSE;
    }
    if (env->RegisterNatives(clazz, gMethods, numMethods) < 0) {
        LOGE("JNI_FALSE");
        return JNI_FALSE;
    }
    LOGI("JNI_TRUE");
    return JNI_TRUE;
}

#define JNI_BRIDGE  "com/skyseraph/xknife/lib/xjni/JNIBridge"

static int registerNatives(JNIEnv *env) {
    const char *kClassName = JNI_BRIDGE;//nativie方法类
    LOGI("registerNatives into %s", kClassName);
    return registerNativeMethods(env, kClassName, gMethods, sizeof(gMethods) / sizeof(gMethods[0]));
}

jint JNI_OnLoad(JavaVM *vm, void *reserved) {
    JNIEnv *env = NULL;
    jint result = -1;
    LOGI("JNI_OnLoad into...");
    if (vm->GetEnv(reinterpret_cast<void **>(&env), JNI_VERSION_1_6) != JNI_OK) {
        return JNI_ERR;
    }

    LOGI("register natives");
    if (registerNatives(env)) {
        LOGI("register success");
        result = JNI_VERSION_1_6;
    } else {
        LOGI("register failed");
    }
    return result;
}

JNIEXPORT jstring JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_getSign(JNIEnv *env,
                                                                         jclass type,
                                                                         jobject context) {
    return getSign(env, context);
}

JNIEXPORT jboolean JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_verifySign(JNIEnv *env,
                                                                             jclass type,
                                                                             jobject context) {
    return verifySignWithContext(env, type, NULL, context);
}

JNIEXPORT jstring JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_stringFromJNI(JNIEnv *env,
                                                                               jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

JNIEXPORT void JNICALL Java_com_skyseraph_xknife_lib_xjni_JNIBridge_registerNatives(JNIEnv *env,
                                                                              jobject /* this */) {
    LOGI("registerNatives into......\n");
}

