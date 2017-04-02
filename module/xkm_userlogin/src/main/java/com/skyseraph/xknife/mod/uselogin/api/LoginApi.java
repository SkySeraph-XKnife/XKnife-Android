package com.skyseraph.xknife.mod.uselogin.api;

import android.content.Context;

import com.skyseraph.xknife.mod.uselogin.imp.model.LoginInfo;


/**
 * Created by SkySeraph on 2016/10/23.
 */
public class LoginApi {

    /**
     * Is logined boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isLogined(Context context) {
        return LoginInfo.isLogined(context);
    }
}
