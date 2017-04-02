//
// Created by SkySeraph on 2016/1/28.
//

#ifndef MTDEMO_LOGS_H
#define MTDEMO_LOGS_H

#include <android/log.h>

#define TAG "XKnife"

#define LOGV(...) __android_log_print(ANDROID_LOG_VERBOSE, TAG, __VA_ARGS__)
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG, TAG, __VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, TAG, __VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN, TAG, __VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR, TAG ,__VA_ARGS__)

#endif //MTDEMO_LOGS_H
