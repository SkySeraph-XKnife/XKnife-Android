package com.skyseraph.xknife.lib.utils.android;

/**
 * Created by SkySeraph on 2016/3/10.
 */
public class Application {

    private static android.app.Application sInstance;

    /**
     * Get android . app . application.
     *
     * @return the android . app . application
     */
    public static android.app.Application get() {
        if (sInstance == null) {
            android.app.Application app = null;
            try {
                app = (android.app.Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
                if (app == null) {
                    throw new IllegalStateException("Static initialization of Applications must be on main thread.");
                }
            } catch (final Exception e) {
                try {
                    app = (android.app.Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
                } catch (final Exception ex) {
                    e.printStackTrace();
                }
            } finally {
                sInstance = app;
            }
        }

        return sInstance;
    }
}
