package com.skyseraph.xknife.mod.uselogin.imp.util.verify;

import android.text.TextUtils;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class UserVerifyUtils {

    /**
     * Is valid email boolean.
     *
     * @param target the target
     * @return the boolean
     */
    public final static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    /**
     * Is valid phone number boolean.
     *
     * @param target the target
     * @return the boolean
     */
    public static final boolean isValidPhoneNumber(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(target).matches();
        }
    }

    /**
     * Is valid password boolean.
     *
     * @param target the target
     * @return the boolean
     */
    public static final boolean isValidPassword(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return target.length() >= 6;
        }
    }

    /**
     * Is valid captcha boolean.
     *
     * @param target the target
     * @return the boolean
     */
    public static final boolean isValidCaptcha(CharSequence target) {
        return isValidPassword(target);
    }
}
