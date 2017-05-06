package com.skyseraph.xknife.c13_3_1;

import android.graphics.drawable.Drawable;

/**
 * Created by SkySeraph on 2016/5/4.
 */
public class AppInfo {

    private String appName;
    private Drawable appIcon;
    private long lastUpdateTime;

    /**
     * Sets app name.
     *
     * @param appName the app name
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Sets app icon.
     *
     * @param appIcon the app icon
     */
    public void setAppIcon(Drawable appIcon) {
        this.appIcon = appIcon;
    }

    /**
     * Sets last update time.
     *
     * @param lastUpdateTime the last update time
     */
    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Gets app icon.
     *
     * @return the app icon
     */
    public Drawable getAppIcon() {
        return appIcon;
    }

    /**
     * Gets last update time.
     *
     * @return the last update time
     */
    public long getLastUpdateTime() {
        return lastUpdateTime;
    }
}
