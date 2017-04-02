package com.skyseraph.xknife.lib.utils.task.task;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by SkySeraph on 2014/3/23.
 */
public class TaskQueue extends Thread {
    private BlockingQueue<Object> mQueue;

    /**
     * Instantiates a new Task queue.
     */
    public TaskQueue() {
        setName(this.getClass().getSimpleName());
        mQueue = new LinkedBlockingQueue<Object>();
    }


    /**
     * Instantiates a new Task queue.
     *
     * @param name the name
     */
    public TaskQueue(String name) {
        this();
        setName(name);
    }

    /**
     * Stop task queue.
     */
    public void stopTaskQueue() {
        // use 'Poison Pill Shutdown' to stop the task queue
        // add a non-Runnable object, which will be recognized as the command
        // by the thread to break the infinite loop
        mQueue.add(new Object());
    }

    /**
     * Schedule task.
     *
     * @param task the task
     */
    public void scheduleTask(Runnable task) {
        mQueue.add(task);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object obj = mQueue.take();

                if (obj instanceof Runnable)
                    ((Runnable) obj).run();

                else
                    break;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
