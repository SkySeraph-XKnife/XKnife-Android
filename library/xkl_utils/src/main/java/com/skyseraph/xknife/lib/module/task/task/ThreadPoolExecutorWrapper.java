package com.skyseraph.xknife.lib.module.task.task;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by SkySeraph on 2014/3/23.
 * an asynchronous task executor(thread pool)
 */
public class ThreadPoolExecutorWrapper {
    private ExecutorService mThreadPoolExecutor;
    private ScheduledThreadPoolExecutor mScheduledThreadPoolExecutor;
    private Handler mMainHandler;

    /**
     * Instantiates a new Thread pool executor wrapper.
     *
     * @param activeThreadCount the active thread count
     * @param maxThreadCount    the max thread count
     * @param maxScheTaskThread the max sche task thread
     */
    public ThreadPoolExecutorWrapper(int activeThreadCount, int maxThreadCount, int maxScheTaskThread) {
        mThreadPoolExecutor = new ThreadPoolExecutor(activeThreadCount, maxThreadCount,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory());

        if (maxScheTaskThread > 0) {
            mScheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(maxScheTaskThread);
        }

        mMainHandler = new Handler(Looper.getMainLooper());
    }

    /**
     * Execute task.
     *
     * @param task the task
     */
    public void executeTask(Runnable task) {
        mThreadPoolExecutor.execute(task);
    }

    /**
     * Submit task future.
     *
     * @param <T>  the type parameter
     * @param task the task
     * @return the future
     */
    public <T> Future<T> submitTask(Callable<T> task) {
        return mThreadPoolExecutor.submit(task);
    }

    /**
     * Schedule task.
     *
     * @param delay the delay
     * @param task  the task
     */
    public void scheduleTask(long delay, Runnable task) {
        mScheduledThreadPoolExecutor.schedule(task, delay, TimeUnit.MILLISECONDS);
    }

    /**
     * Schedule task at fixed rate ignoring task running time.
     *
     * @param initialDelay the initial delay
     * @param period       the period
     * @param task         the task
     */
    public void scheduleTaskAtFixedRateIgnoringTaskRunningTime(long initialDelay, long period, Runnable task) {
        mScheduledThreadPoolExecutor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * Schedule task at fixed rate including task running time.
     *
     * @param initialDelay the initial delay
     * @param period       the period
     * @param task         the task
     */
    public void scheduleTaskAtFixedRateIncludingTaskRunningTime(long initialDelay, long period, Runnable task) {
        mScheduledThreadPoolExecutor.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.MILLISECONDS);
    }

    /**
     * Remove scheduled task boolean.
     *
     * @param task the task
     * @return the boolean
     */
    public boolean removeScheduledTask(Runnable task) {
        return mScheduledThreadPoolExecutor.remove(task);
    }

    /**
     * Schedule task on ui thread.
     *
     * @param delay the delay
     * @param task  the task
     */
    public void scheduleTaskOnUiThread(long delay, Runnable task) {
        mMainHandler.postDelayed(task, delay);
    }

    /**
     * Remove scheduled task on ui thread.
     *
     * @param task the task
     */
    public void removeScheduledTaskOnUiThread(Runnable task) {
        mMainHandler.removeCallbacks(task);
    }

    /**
     * Run task on ui thread.
     *
     * @param task the task
     */
    public void runTaskOnUiThread(Runnable task) {
        mMainHandler.post(task);
    }

    /**
     * Shutdown.
     */
    public void shutdown() {
        if (mThreadPoolExecutor != null) {
            mThreadPoolExecutor.shutdown();
            mThreadPoolExecutor = null;
        }

        if (mScheduledThreadPoolExecutor != null) {
            mScheduledThreadPoolExecutor.shutdown();
            mScheduledThreadPoolExecutor = null;
        }
    }
}
