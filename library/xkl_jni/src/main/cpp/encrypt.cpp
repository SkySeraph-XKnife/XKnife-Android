//
// Created by SkySeraph on 2016/1/28.
//

#include <string>
#include "encrypt.h"

/**发布的App签名。 签名的SHA1值获取步骤：
 * 1. 通过命令：keytool -list -rfc -keystore your.keystore
 * 2. 用BASE64 解密
 * 在线转码工具：http://www1.tc711.com/tool/BASE64.htm
 * */
const char *APP_RELEASE_SIGN = "xxxxxxx";

/**
 *服务器通信秘钥
 */
const char *AUTH_KEY = "authKey";

/**
 * 发布的app 签名 的HashCode
 */
const int RELEASE_SIGN_HASHCODE = -334552125;

// 花指令
std::string reverseString(std::string s) {
    int len = s.length();
    int mid = len / 2;
    for (int i = 0; i < mid; i++) {
        int t = len - i - 1;
        s[i] ^= s[t];
        s[t] ^= s[i];
        s[i] ^= s[t];
    }
    return s;
}

jobject getApplication(JNIEnv *env) {
    jclass localClass = env->FindClass("android/app/ActivityThread");
    if (localClass != NULL) {
        LOGI("class have find");
        jmethodID getapplication = env->GetStaticMethodID(localClass, "currentApplication",
                                                          "()Landroid/app/Application;");
        if (getapplication != NULL) {
            jobject application = env->CallStaticObjectMethod(localClass, getapplication);
            return application;
        }
        return NULL;
    }
    return NULL;
}

jobject getInstance(JNIEnv *env, jclass obj_class) {

    jmethodID construction_id = env->GetMethodID(obj_class, "<init>", "()V");
    jobject obj = env->NewObject(obj_class, construction_id);
    return obj;
}

jobject signature_object;
jclass signature_class;

jstring getSign(JNIEnv *env, jobject context) {

    jclass context_class = env->GetObjectClass(context);

    // 获取 getPackageManager 方法的 ID
    jmethodID methodId = env->GetMethodID(context_class, "getPackageManager",
                                          "()Landroid/content/pm/PackageManager;");
    // 获取PackageManager对象
    jobject package_manager_object = env->CallObjectMethod(context, methodId);
    if (package_manager_object == NULL) {
        LOGE("getPackageManager() Failed!");
        return false;
    }
    // 获取 getPackageName 方法的 ID
    methodId = env->GetMethodID(context_class, "getPackageName", "()Ljava/lang/String;");
    // 获取包名
    jstring package_name_string = (jstring) env->CallObjectMethod(context, methodId);
    if (package_name_string == NULL) {
        LOGE("getPackageName() Failed!");
        return false;
    }
    env->DeleteLocalRef(context_class);

    // 获取getPackageInfo 方法的 ID
    jclass pack_manager_class = env->GetObjectClass(package_manager_object);
    methodId = env->GetMethodID(pack_manager_class, "getPackageInfo",
                                "(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;");
    env->DeleteLocalRef(pack_manager_class);
    // 获取应用包的信息
    jobject package_info_object = env->CallObjectMethod(package_manager_object, methodId,
                                                        package_name_string, 0x40);
    if (package_info_object == NULL) {
        LOGE("getPackageInfo() Failed!");
        return false;
    }
    env->DeleteLocalRef(package_manager_object);

    // 获取PackageInfo 类
    jclass package_info_class = env->GetObjectClass(package_info_object);
    // 获取签名数组属性的 ID
    jfieldID fieldId = env->GetFieldID(package_info_class, "signatures",
                                       "[Landroid/content/pm/Signature;");
    env->DeleteLocalRef(package_info_class);
    // 得到签名数组
    jobjectArray signature_object_array = (jobjectArray) env->GetObjectField(package_info_object,
                                                                             fieldId);
    if (signature_object_array == NULL) {
        LOGE("PackageInfo.signatures[] is null");
        return false;
    }
    // 得到签名
    signature_object = env->GetObjectArrayElement(signature_object_array, 0);
    env->DeleteLocalRef(package_info_object);

    // 获取sign
    signature_class = env->GetObjectClass(signature_object);
    methodId = env->GetMethodID(signature_class, "toCharsString", "()Ljava/lang/String;");
    env->DeleteLocalRef(signature_class);
    // 获取签名字符
    jstring signature_jstirng = (jstring) env->CallObjectMethod(signature_object, methodId);

    return signature_jstirng;
}

bool verifySignWithContext(JNIEnv *env, jclass clz, jobject obj, jobject context) {
    jstring signature_jstirng = getSign(env, context);
    const char *sign = env->GetStringUTFChars(signature_jstirng, NULL);
    LOGD("sign is %s\n", sign);

    // Method 1: 检查签名字符串
    if (strcmp(sign, APP_RELEASE_SIGN) == 0) {
        LOGE("验证通过");
        return true;
    }

    // Method 2: 检查签名的hashCode
    jmethodID int_hashcode = env->GetMethodID(signature_class, "hashCode", "()I");
    jint hashCode = env->CallIntMethod(signature_object, int_hashcode);
    if (hashCode == RELEASE_SIGN_HASHCODE) {
        LOGE("验证通过 %s", (env)->NewStringUTF(AUTH_KEY));
        return true;
    }

    LOGE("验证失败");
    return false;
}

void native_verifySign(JNIEnv *env, jobject obj) {
    LOGI("verifySign into......\n");
    jobject context = getApplication(env);
    verifySignWithContext(env, NULL, obj, context);
}

