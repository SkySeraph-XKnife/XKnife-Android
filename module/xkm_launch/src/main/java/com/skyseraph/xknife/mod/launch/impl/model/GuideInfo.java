package com.skyseraph.xknife.mod.launch.impl.model;

import android.content.Context;

import com.skyseraph.xknife.lib.utils.android.SharedPreferencesUtils;

/**
 * Created by SkySeraph on 2016/10/10.
 */
public class GuideInfo {

    private static final String FIRST_SHOW = "FIRST_SHOW";

    private static final String FILE_NAME = "GUIDEINFO";

    /**
     * Is guide show boolean.
     *
     * @param context the context
     * @return the boolean
     */
    public static boolean isGuideShow(Context context) {
        return SharedPreferencesUtils.getBoolean(context, FILE_NAME, FIRST_SHOW, false);
    }

    /**
     * Sets guide show.
     *
     * @param context the context
     * @param show    the show
     */
    public static void setGuideShow(Context context, boolean show) {
        SharedPreferencesUtils.putBoolean(context, FILE_NAME, FIRST_SHOW, show);
    }
}
