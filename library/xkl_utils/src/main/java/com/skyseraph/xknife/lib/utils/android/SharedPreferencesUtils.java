package com.skyseraph.xknife.lib.utils.android;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

/**
 * Created by SkySeraph on 2015/3/2.
 * SharedPreferences操作工具类 （单线程）
 */
public class SharedPreferencesUtils {

    private SharedPreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * Put int.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @param v        the v
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void putInt(Context context, String fileName, String k, int v) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putInt(k, v);
        editor.apply();
    }

    /**
     * Put boolean.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @param v        the v
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void putBoolean(Context context, String fileName, String k,
                           boolean v) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putBoolean(k, v);
        editor.apply();
    }

    /**
     * Put string.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @param v        the v
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void putString(Context context, String fileName, String k,
                           String v) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(k, v);
        editor.apply();
    }

    /**
     * Gets int.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @return the int
     */
    public static int getInt(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getInt(k, 0);
    }

    /**
     * Gets int.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @param defv     the defv
     * @return the int
     */
    public static int getInt(Context context, String fileName, String k,
                             int defv) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getInt(k, defv);
    }

    /**
     * Gets boolean.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @return the boolean
     */
    public static boolean getBoolean(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getBoolean(k, false);
    }

    /**
     * Gets boolean.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @param defBool  the def bool
     * @return the boolean
     */
    public static boolean getBoolean(Context context, String fileName,
                                     String k, boolean defBool) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getBoolean(k, defBool);
    }

    /**
     * Gets string.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @return the string
     */
    public static String getString(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getString(k, null);
    }

    /**
     * Gets string.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     * @param defV     the def v
     * @return the string
     */
    public static String getString(Context context, String fileName, String k,
                                   String defV) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        return preference.getString(k, defV);
    }

    /**
     * Remove.
     *
     * @param context  the context
     * @param fileName the file name
     * @param k        the k
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void remove(Context context, String fileName, String k) {
        SharedPreferences preference = context.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.remove(k);
        editor.apply();
    }

    /**
     * Clean.
     *
     * @param cxt      the cxt
     * @param fileName the file name
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static void clean(Context cxt, String fileName) {
        SharedPreferences preference = cxt.getSharedPreferences(fileName,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.apply();
    }
}
