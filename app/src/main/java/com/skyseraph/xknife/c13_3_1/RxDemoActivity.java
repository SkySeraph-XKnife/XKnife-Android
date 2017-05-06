package com.skyseraph.xknife.c13_3_1;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.skyseraph.xknife.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by SkySeraph on 2016/5/5.
 */
public class RxDemoActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    /**
     * The Pull refresh.
     */
    @BindView(R.id.pull_down_srl)
    SwipeRefreshLayout pullRefresh;
    /**
     * The App list.
     */
    @BindView(R.id.rv_app_list)
    RecyclerView appList;

    private List<AppInfo> appInfoList;
    private AppListAdapter appListAdapter;
    /**
     * The Pm.
     */
    PackageManager pm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_list);
        ButterKnife.bind(this);
        pm = this.getPackageManager();
        appList = (RecyclerView) findViewById(R.id.rv_app_list);
        initView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        appList.setLayoutManager(linearLayoutManager);
        appInfoList = new ArrayList<>();
        appListAdapter = new AppListAdapter(appInfoList);
        appList.setAdapter(appListAdapter);

        pullRefresh.setOnRefreshListener(this);
        pullRefresh.post(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        });
    }

    @Override
    public void onRefresh() {
        if (appInfoList != null) {
            appInfoList.clear();
            appListAdapter.notifyDataSetChanged();
        }
        loadApp();
    }

    private List<ApplicationInfo> getApplicationInfoList(final PackageManager pm) {
        return pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
    }

    private void loadApp() {

        // 1. 创建 Observable(subscribe法)
        Observable.create(new Observable.OnSubscribe<ApplicationInfo>() {
            @Override
            public void call(Subscriber<? super ApplicationInfo> subscriber) {
                if (subscriber.isUnsubscribed()) { // 如果已经取消订阅, 直接退出
                    return;
                }
                for (ApplicationInfo info : getApplicationInfoList(pm)) {
                    subscriber.onNext(info); // 发布事件通知订阅者
                }
                subscriber.onCompleted(); // 事件通知完成
            }
        }).filter(new Func1<ApplicationInfo, Boolean>() { // 2. 过滤非系统应用
            @Override
            public Boolean call(ApplicationInfo applicationInfo) {
                return (applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) <= 0;
            }
        }).map(new Func1<ApplicationInfo, AppInfo>() { // 3. 对象变换（ApplicationInfo -> AppInfo）

            @Override
            public AppInfo call(ApplicationInfo applicationInfo) {
                AppInfo info = new AppInfo();
                info.setAppIcon(applicationInfo.loadIcon(pm));
                info.setAppName(applicationInfo.loadLabel(pm).toString());
                return info;
            }
        }).subscribeOn(Schedulers.io()) // Observable运行在新线程
                .onBackpressureBuffer()  // 通过缓存避免生产者发射数据的速度比消费者处理的快
                .observeOn(AndroidSchedulers.mainThread()) // subscriber运行在Android 主线程
                .subscribe(new Subscriber<AppInfo>() { // 4. 订阅
                    @Override
                    public void onCompleted() {
                        appListAdapter.notifyDataSetChanged();
                        pullRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        pullRefresh.setRefreshing(false);
                    }

                    @Override
                    public void onNext(AppInfo appInfo) {
                        appInfoList.add(appInfo);
                    }
                });
    }

    private void loadAppByLambda() {
        // 1. 创建 Observable(lambda写法)

    }
}
