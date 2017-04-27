package com.skyseraph.xknife.lib.module.task.task;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * Created by SkySeraph on 2014/3/23.
 * an asynchronous task executor(thread pool)
 */
public class TaskExecutor {
    private static ThreadPoolExecutorWrapper sThreadPoolExecutorWrapper;

    /**
     * Init.
     *
     * @param maxTaskThread     the max task thread
     * @param maxIdleTaskThread the max idle task thread
     * @param maxScheTaskThread the max sche task thread
     */
    public static void init(int maxTaskThread, int maxIdleTaskThread, int maxScheTaskThread) {
        if (sThreadPoolExecutorWrapper == null) {
            sThreadPoolExecutorWrapper = new ThreadPoolExecutorWrapper(maxTaskThread, maxIdleTaskThread, maxScheTaskThread);
        }
    }

    /**
     * Execute task.
     *
     * @param task the task
     */
    public static void executeTask(Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.executeTask(task);
        }
    }

    /**
     * Submit task future.
     *
     * @param <T>  the type parameter
     * @param task the task
     * @return the future
     */
    public static <T> Future<T> submitTask(Callable<T> task) {
        if (sThreadPoolExecutorWrapper != null) {
            return sThreadPoolExecutorWrapper.submitTask(task);
        }
        return null;
    }

    /**
     * Schedule task.
     *
     * @param delay the delay
     * @param task  the task
     */
    public static void scheduleTask(long delay, Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.scheduleTask(delay, task);
        }
    }

    /**
     * Schedule task at fixed rate ignoring task running time.
     *
     * @param initialDelay the initial delay
     * @param period       the period
     * @param task         the task
     */
    public static void scheduleTaskAtFixedRateIgnoringTaskRunningTime(long initialDelay, long period, Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.scheduleTaskAtFixedRateIgnoringTaskRunningTime(initialDelay, period, task);
        }
    }

    /**
     * Schedule task at fixed rate including task running time.
     *
     * @param initialDelay the initial delay
     * @param period       the period
     * @param task         the task
     */
    public static void scheduleTaskAtFixedRateIncludingTaskRunningTime(long initialDelay, long period, Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.scheduleTaskAtFixedRateIncludingTaskRunningTime(initialDelay, period, task);
        }
    }

    /**
     * Remove scheduled task boolean.
     *
     * @param task the task
     * @return the boolean
     */
    public static boolean removeScheduledTask(Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            return sThreadPoolExecutorWrapper.removeScheduledTask(task);
        }
        return false;
    }

    /**
     * Schedule task on ui thread.
     *
     * @param delay the delay
     * @param task  the task
     */
    public static void scheduleTaskOnUiThread(long delay, Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.scheduleTaskOnUiThread(delay, task);
        }
    }

    /**
     * Remove scheduled task on ui thread.
     *
     * @param task the task
     */
    public static void removeScheduledTaskOnUiThread(Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.removeScheduledTaskOnUiThread(task);
        }
    }

    /**
     * Run task on ui thread.
     *
     * @param task the task
     */
    public static void runTaskOnUiThread(Runnable task) {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.runTaskOnUiThread(task);
        }
    }

    /**
     * Shutdown.
     */
    public static void shutdown() {
        if (sThreadPoolExecutorWrapper != null) {
            sThreadPoolExecutorWrapper.shutdown();
            sThreadPoolExecutorWrapper = null;
        }
    }
}
