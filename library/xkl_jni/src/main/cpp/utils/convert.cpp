//
// Created by SkySeraph on 2016/1/28.
//

#include <string>
#include "convert.h"

/**
 * char* to jstring
 */
jstring toString(JNIEnv *env, jbyteArray byteArray) {
    jclass string_cls = env->FindClass("java/lang/String");
    jmethodID new_string_mid = env->GetMethodID(string_cls, "<init>",
                                                "([BLjava/lang/String;)V");
    return reinterpret_cast<jstring>(env->NewObject(string_cls, new_string_mid, byteArray,
                                                    env->NewStringUTF(UTF_8)));
}

/**
 * char* to Bytes
 */
jbyteArray toBytes(JNIEnv *env, const char *bytes) {
    jclass string_cls = env->FindClass("java/lang/String");
    jmethodID get_bytes_mid = env->GetMethodID(string_cls, "getBytes",
                                               "(Ljava/lang/String;)[B");
    return reinterpret_cast<jbyteArray>(env->CallObjectMethod(env->NewStringUTF(bytes),
                                                              get_bytes_mid,
                                                              env->NewStringUTF(UTF_8)));
}

/**
 * jstring to Bytes
 */
jbyteArray toBytes(JNIEnv *env, jstring string) {
    jclass string_cls = env->FindClass("java/lang/String");
    jmethodID get_bytes_mid = env->GetMethodID(string_cls, "getBytes",
                                               "(Ljava/lang/String;)[B");
    return reinterpret_cast<jbyteArray>(env->CallObjectMethod(string, get_bytes_mid,
                                                              env->NewStringUTF(UTF_8)));
}

/**
 * jstring to char*
 */
char *jstringTostring(JNIEnv *env, jstring jstr) {
    char *rtn = NULL;
    jclass clsstring = env->FindClass("java/lang/String");
    jstring strencode = env->NewStringUTF("utf-8");
    jmethodID mid = env->GetMethodID(clsstring, "getBytes", "(Ljava/lang/String;)[B");
    jbyteArray barr = (jbyteArray) env->CallObjectMethod(jstr, mid, strencode);
    jsize alen = env->GetArrayLength(barr);
    jbyte *ba = env->GetByteArrayElements(barr, JNI_FALSE);
    if (alen > 0) {
        rtn = (char *) malloc(alen + 1);
        memcpy(rtn, ba, alen);
        rtn[alen] = 0;
    }
    env->ReleaseByteArrayElements(barr, ba, 0);
    return rtn;
}

/**
 * toHex
 */
jstring toHex(JNIEnv *env, jbyteArray digested_bytes) {
    jclass big_integer_cls = env->FindClass("java/math/BigInteger");
    jmethodID new_big_integer_mid = env->GetMethodID(big_integer_cls, "<init>",
                                                     "(I[B)V");
    jobject big_integer_obj = env->NewObject(big_integer_cls,
                                             new_big_integer_mid, 1, digested_bytes);
    env->DeleteLocalRef(digested_bytes);
    jmethodID to_String_mid = env->GetMethodID(big_integer_cls, "toString",
                                               "(I)Ljava/lang/String;");
    env->DeleteLocalRef(big_integer_cls);
    return reinterpret_cast<jstring>(env->CallObjectMethod(big_integer_obj,
                                                           to_String_mid, 16));
}


/**
 * getDigestedBytes
 */
jbyteArray getDigestedBytes(JNIEnv *env, jbyteArray complex_bytes) {
    static jobject satic_message_digest_obj = __null;
    jclass message_digest_cls = env->FindClass("java/security/MessageDigest");
    jmethodID get_instance_mid = env->GetStaticMethodID(message_digest_cls,
                                                        "getInstance",
                                                        "(Ljava/lang/String;)Ljava/security/MessageDigest;");
    if (satic_message_digest_obj == __null) {
        jobject local_message_digest_obj = env->CallStaticObjectMethod(
                message_digest_cls, get_instance_mid, env->NewStringUTF("MD5"));
        satic_message_digest_obj = env->NewGlobalRef(local_message_digest_obj);
        env->DeleteLocalRef(local_message_digest_obj);
    }
    jmethodID digest_mid = env->GetMethodID(message_digest_cls, "digest",
                                            "([B)[B");
    env->DeleteLocalRef(message_digest_cls);
    return reinterpret_cast<jbyteArray>(env->CallObjectMethod(
            satic_message_digest_obj, digest_mid, complex_bytes));
}


jstring getMD5(JNIEnv *env, jstring jInfo) {
    jbyteArray digested_bytes = getDigestedBytes(env, toBytes(env, jInfo));
    return toHex(env, digested_bytes);
}

