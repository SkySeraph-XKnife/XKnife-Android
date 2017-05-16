package com.skyseraph.xknife.c9_4;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import com.skyseraph.xknife.AppApplication;
import com.skyseraph.xknife.R;
import com.squareup.leakcanary.RefWatcher;

import java.lang.ref.WeakReference;


/**
 * Created by SkySeraph on 2016/5/15.
 */
public class LeakActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);

        // ①使用 RefWatcher 监控 Activity
        RefWatcher refWatcher = AppApplication.getRefWatcher(this);
        refWatcher.watch(this);

        // ② LeakSingleton
        TextView text = (TextView) findViewById(R.id.tv);
        LeakSingleton.getInstance(this.getApplication()).setRetainedTextView(text);

        // ③
        View button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAsyncTask();
            }
        });

        // ④ LeakHandler (Wrong)
        leakyHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 原因： 当前Activity finish后，延迟执行任务的Message还会继续存在于主线程中，它持有LeakActivity造成其无法回收而内存泄漏
            }
        }, 1000 * 60 * 1);

        // ④ LeakHandler (Right)
        notLeakHandler.postDelayed(sRunnable, 1000 * 60 * 1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // ②
        LeakSingleton.getInstance(this.getApplication()).removeRetainedTextView();
        // ④
        notLeakHandler.removeCallbacks(sRunnable);
    }

    // ③ Leak Anonymous Classes
    private void startAsyncTask() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                SystemClock.sleep(20000);
                return null;
            }
        }.execute();
    }

    // ④(Wrong 非静态内部类，会持有外部类的引用LeakActivity)
    private final Handler leakyHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO: 2016/5/15
        }
    };

    // ④ (Right 静态内部类 + WeakReference)
    // 回收时会回收Handler 持有的对象，再通过removeCallbacks对延迟消息进行移除
    private final NotLeakHandler notLeakHandler = new NotLeakHandler(this);

    private static class NotLeakHandler extends Handler {
        private final WeakReference<LeakActivity> activity;

        /**
         * Instantiates a new Not leak handler.
         *
         * @param activity the activity
         */
        public NotLeakHandler(LeakActivity activity) {
            this.activity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            LeakActivity activity = this.activity.get();
            if (activity != null) { // 注意判空
                // TODO: 2016/5/15
            }
        }
    }

    private static final Runnable sRunnable = new Runnable() {
        @Override
        public void run() {
            // TODO: 2016/5/15
        }
    };


}
