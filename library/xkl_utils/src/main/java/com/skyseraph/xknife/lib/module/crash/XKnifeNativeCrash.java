package com.skyseraph.xknife.lib.module.crash;

import android.content.Context;

/**
 * Created by SkySeraph on 2016/2/2.
 */

public class XKnifeNativeCrash {

    private static boolean mIsSuccess = false;

    public static void setJniErrorDir(Context context) {
        if (mIsSuccess) {
        }
    }

    private static native void setErrorDir(String dir);

    static {
        try {
            System.loadLibrary("libs/crashlog");
            mIsSuccess = true;
        } catch (Throwable e) {
            mIsSuccess = false;
        }
    }
}
