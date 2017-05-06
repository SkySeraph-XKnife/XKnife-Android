package com.skyseraph.xknife;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by  on 2016/4/12.
 */
public class Test {

    /**
     * The type Weak runnable.
     */
    public class WeakRunnable implements Runnable {
        private WeakReference<Runnable> mWr;

        /**
         * Instantiates a new Weak runnable.
         *
         * @param runnable the runnable
         */
        public WeakRunnable(Runnable runnable) {
            mWr = new WeakReference<Runnable>(runnable);
        }

        public void run() {
            Runnable runnable = mWr.get();
            if (runnable != null) {
                runnable.run();
            }
        }
    }
}







