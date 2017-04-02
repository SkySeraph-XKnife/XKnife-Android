package com.skyseraph.xknife.lib.xjni;

import android.content.Context;

/**
 * Created by SkySeraph on 2016/1/28.
 */
public class JNIBridge {

    static {
        System.loadLibrary("native-lib"); // 加载动态库
        registerNatives();
    }

    private static String str;
    private static String key;
    private static boolean isVerify = false;

    public static boolean isVerify(Context context) {
        return verifySign(context);
    }

    public static String getKey(Context context) {
        if (key == null) {
            key = getSign(context);
        }
        return key;
    }

    public static String getStr() {
        if (str == null) {
            str = stringFromJNI();
        }
        return str;
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    private static native String stringFromJNI();

    private static native String getSign(Context context);

    private static native boolean verifySign(Context context);

    private static native void registerNatives();

    public native void verifySign();
}
