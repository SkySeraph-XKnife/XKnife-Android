package debug;

import android.app.Application;

/**
 * Created by Bob on 2016/10/12 23:45
 * SkySeraph00@126.com
 */

public class ModuleAppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        L.mLogEnable = BuildConfig.DEBUG;
    }
}

