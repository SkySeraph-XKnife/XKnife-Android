package com.skyseraph.xknife.lib.utils.android.base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.skyseraph.xknife.lib.utils.android.PermissionUtils;
import com.skyseraph.xknife.lib.utils.patterns.Singleton;


/**
 * Created by SkySeraph on 2015/12/17.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseCallback {

    public Context getContext() {
        return super.getApplicationContext();
    }

    public Activity getActivity() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Singleton.getSingleton(ActivityManager.class).addActivity(this);
        if (getLayoutId() > 0) {
            setContentView(getLayoutId());
        }
        initData(savedInstanceState);
        initView();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Singleton.getSingleton(ActivityManager.class).finishActivity();
    }

    /**
     * Android M 全局权限申请回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * Gets view by id.
     *
     * @param <E> the type parameter
     * @param id  the id
     * @return the view by id
     */
    public final <E extends View> E getViewById(int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    public <T extends android.support.v4.app.Fragment> T findSupportFragment(Class<T> fragmentClass) {
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.Fragment fragment = fragmentManager.findFragmentByTag(fragmentClass.getName());
        return (T) fragment;
    }

    public <T extends android.support.v4.app.Fragment> T newSupportFragment(Class<T> fragmentClass, Bundle args) {
        android.support.v4.app.Fragment fragment = android.support.v4.app.Fragment.instantiate(getContext(), fragmentClass.getName(), args);
        fragment.setArguments(args);
        return (T) fragment;
    }
}
