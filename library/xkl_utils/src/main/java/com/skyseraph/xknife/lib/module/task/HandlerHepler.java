package com.skyseraph.xknife.lib.module.task;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * Created by SkySeraph on 2015/3/23.
 */
public class HandlerHepler {

    private final static Handler mainHandler=new Handler(Looper.getMainLooper());
    private final static HandlerThread business0HandlerThread = new HandlerThread("Business0Handler");
    private static Handler business0Handler;
    private final static HandlerThread business1HandlerThread = new HandlerThread("Business1Handler");
    private static Handler business1Handler;

    /**
     * Gets main handler.
     *
     * @return the main handler
     */
    public static Handler getMainHandler() {
        return mainHandler;
    }

    /**
     * Gets business 0 handler.
     *
     * @return the business 0 handler
     */
    public static Handler getBusiness0Handler() {
        if (business0Handler == null) {
            synchronized(HandlerHepler.class){
                if(business0Handler==null) {
                    business0HandlerThread.start();
                    business0Handler = new Handler(business0HandlerThread.getLooper());
                }
            }
        }
        return business0Handler;
    }

    /**
     * Gets business 1 handler.
     *
     * @return the business 1 handler
     */
    public static Handler getBusiness1Handler() {
        if (business1Handler == null) {
            synchronized(HandlerHepler.class){
                if(business1Handler==null) {
                    business1HandlerThread.start();
                    business1Handler = new Handler(business1HandlerThread.getLooper());
                }
            }
        }
        return business1Handler;
    }

}
