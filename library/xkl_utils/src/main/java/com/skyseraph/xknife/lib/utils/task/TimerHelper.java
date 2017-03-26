package com.skyseraph.xknife.lib.utils.task;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SkySeraph on 2015/3/23.
 */
public abstract class TimerHelper {

    private Timer timer = null;

    /**
     * Start.
     *
     * @param delay  the delay
     * @param period the period
     */
    public void start(long delay, long period) {
        stop();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                TimerHelper.this.run();
            }
        }, delay, period);
    }

    /**
     * Stop.
     */
    public void stop() {
        if (timer != null) {

            timer.cancel();
            timer = null;
        }
    }

    /**
     * Instantiates a new Timer helper.
     */
    public TimerHelper() {
        timer = null;
    }

    /**
     * Run.
     */
    public abstract void run();
}
