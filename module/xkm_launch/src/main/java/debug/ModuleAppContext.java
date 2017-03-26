package debug;

import android.app.Application;

/**
 * Created by SkySeraph on 2016/10/12 23:45
 */

public class ModuleAppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        L.mLogEnable = BuildConfig.DEBUG;
    }
}

