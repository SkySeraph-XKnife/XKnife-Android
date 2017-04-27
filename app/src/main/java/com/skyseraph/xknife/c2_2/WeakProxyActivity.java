package com.skyseraph.xknife.c2_2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.skyseraph.xknife.lib.module.task.task.TaskExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob on 2017/4/26.
 */

public class WeakProxyActivity extends AppCompatActivity implements WeakProxy.IWeakHost {

    List<Object> referList = new ArrayList<Object>();
    private int xx = 112;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TaskExecutor.executeTask(WeakProxy.wrap(this, new Runnable() {
            public void run() {
                Log.d("MainActivity", "xx:" + xx); // 引用了外部成员变量
            }
        }));
    }

    @Override
    public void referObject(Object obj) {
        referList.add(obj);
    }
}