package com.skyseraph.xknife.mod.uselogin.imp.model;

/**
 * Created by SkySeraph on 2016/10/23.
 */
public class LoginStatus {

    /**
     * The enum Failed status.
     */
    public enum FailedStatus {
        /**
         * Illegality account failed status.
         */
        ILLEGALITY_ACCOUNT,
        /**
         * Illegality passwd failed status.
         */
        ILLEGALITY_PASSWD,
    }

    /**
     * The enum Check type.
     */
    public enum CheckType {
        /**
         * Account check type.
         */
        ACCOUNT,
        /**
         * Phone check type.
         */
        PHONE,
        /**
         * Passwd check type.
         */
        PASSWD,
        /**
         * Captcha check type.
         */
        CAPTCHA
    }


    /**
     * The enum Login type.
     */
    public enum LoginType {
        /**
         * Login login type.
         */
        LOGIN,
        /**
         * Forget login type.
         */
        FORGET,
        /**
         * Register login type.
         */
        REGISTER
    }

    /**
     * The interface Login listener.
     */
    public interface LoginListener {
        /**
         * On complete.
         *
         * @param strings the strings
         */
        void onComplete(String... strings);
    }
}
