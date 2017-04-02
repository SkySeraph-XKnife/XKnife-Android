package com.skyseraph.xknife.lib.utils.android;

/**
 * Created by Bob on 2015/4/2.
 */
public class JniBridge {

    private JniBridge() {

    }

    /**
     * Load library.
     *
     * @param sdkSoPath the sdk so path
     */
    public static void loadLibrary(String sdkSoPath) {
        try {
            System.load(sdkSoPath);
        } catch (Throwable e) {
        }
    }

    /**
     * JIN模块初始化
     *
     * @param scriptLibPath the script lib path
     * @return the boolean
     */
    public static boolean initJNI(String scriptLibPath) {
        if (true) {
            return true;

        } else {
            return false;
        }
    }
}
