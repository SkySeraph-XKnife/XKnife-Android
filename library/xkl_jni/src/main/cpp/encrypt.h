//
// Created by SkySeraph on 2016/1/28.
//

#ifndef MTDEMO_ENCRYPT_H
#define MTDEMO_ENCRYPT_H

#include <jni.h>
#include "utils/logs.h"

#ifdef __cplusplus

extern "C" {

std::string reverseString(std::string s);
jstring getSign(JNIEnv *env, jobject context);
jobject getApplication(JNIEnv *env);
bool verifySignWithContext(JNIEnv *env, jclass clz, jobject obj, jobject context);
void native_verifySign(JNIEnv *env, jobject obj);
};

#endif

#endif //MTDEMO_ENCRYPT_H
