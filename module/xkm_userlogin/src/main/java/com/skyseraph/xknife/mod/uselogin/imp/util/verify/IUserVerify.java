package com.skyseraph.xknife.mod.uselogin.imp.util.verify;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public interface IUserVerify {

    /**
     * Verify user name boolean.
     *
     * @param phoneOrEmail the phone or email
     * @return the boolean
     */
    boolean verifyUserName(String phoneOrEmail);

    /**
     * Verify password boolean.
     *
     * @param password the password
     * @return the boolean
     */
    boolean verifyPassword(String password);

    /**
     * Verify captcha boolean.
     *
     * @param captcha the captcha
     * @return the boolean
     */
    boolean verifyCaptcha(String captcha);

    /**
     * Verify phone boolean.
     *
     * @param phone the phone
     * @return the boolean
     */
    boolean verifyPhone(String phone);

}
