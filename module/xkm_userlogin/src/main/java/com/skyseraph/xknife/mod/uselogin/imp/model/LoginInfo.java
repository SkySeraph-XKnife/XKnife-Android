package com.skyseraph.xknife.mod.uselogin.imp.model;

import android.content.Context;

import com.skyseraph.xknife.lib.utils.android.SharedPreferencesUtils;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class LoginInfo {

    private static final String IS_LOGINED = "IS_LOGINED";

    private static final String FILE_NAME = "LOGININFO";

    /**
     * Is logined boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isLogined(Context context) {
        return SharedPreferencesUtils.getBoolean(context, FILE_NAME, IS_LOGINED, false);
    }

    /**
     * Sets logined.
     *
     * @param context the context
     * @param show    the show
     */
    public static void setLogined(Context context, boolean show) {
        SharedPreferencesUtils.putBoolean(context, FILE_NAME, IS_LOGINED, show);
    }
}
