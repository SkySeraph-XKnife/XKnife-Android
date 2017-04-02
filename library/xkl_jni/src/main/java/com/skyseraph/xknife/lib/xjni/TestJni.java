package com.skyseraph.xknife.lib.xjni;

import android.content.Context;

/**
 * Created by SkySeraph on 2016/1/28.
 */

public class TestJni {

    public static String getKey(Context context) {
        return JNIBridge.getKey(context);
    }

    public static boolean isVerify(Context context) {
        return JNIBridge.isVerify(context);
    }

    public static String getTest(Context context) {
        return JNIBridge.getStr();
    }

}
