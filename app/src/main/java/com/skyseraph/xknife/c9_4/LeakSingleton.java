package com.skyseraph.xknife.c9_4;

import android.content.Context;
import android.widget.TextView;

import com.skyseraph.xknife.R;


/**
 * Created by SkySeraph on 2016/5/15.
 */
public class LeakSingleton {


    private static LeakSingleton instance;
    private Context context;
    private TextView textView;

    private static LeakSingleton sInstance;

    private LeakSingleton(Context context) {
        // Error: 如果传入为Activity的Context，当Context对应的Activity退出后，而该Context被单例持有，其对应Activity的内存不会被释放
        this.context = context;
        // Right: 使用Application 的context
//        this.context = context.getApplicationContext();
        // Right：不传参，直接全局获取
//        this.context = BaseApplication.getContext();
    }

    /**
     * Gets instance.
     *
     * @param context the context
     * @return the instance
     */
    public static LeakSingleton getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new LeakSingleton(context);
        }
        return sInstance;
    }

    /**
     * Sets retained text view.
     *
     * @param tv the tv
     */
    public void setRetainedTextView(TextView tv) {
        textView = tv;
        textView.setText(context.getString(R.string.app_name));
    }

    /**
     * Remove retained text view.
     */
    public void removeRetainedTextView() {
        textView = null;
    }
}
