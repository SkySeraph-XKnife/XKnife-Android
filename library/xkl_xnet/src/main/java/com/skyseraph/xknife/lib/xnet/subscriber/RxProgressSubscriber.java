package com.skyseraph.xknife.lib.xnet.subscriber;

import android.content.Context;

import com.skyseraph.xknife.lib.xnet.utils.ProgressDialog;


/**
 * Created by SkySeraph on 2016/5/4.
 * Http请求开始时，自动显示一个ProgressDialog
 *
 * @param <T> the type parameter
 */
public abstract class RxProgressSubscriber<T> extends RxSubscriber implements IProgressListener {

    private Context context;
    private ProgressDialog progressDialog;

    /**
     * Instantiates a new Rx progress subscriber.
     *
     * @param context the context
     */
    public RxProgressSubscriber(Context context) {
        this.context = context;
        progressDialog = new ProgressDialog(context, this, true);
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
    }

    @Override
    public void onCancel() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe(); //取消observable的订阅
        }
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        dismissProgressDialog();
    }

    private void showProgressDialog() {
        if (progressDialog != null) {
            progressDialog.obtainMessage(ProgressDialog.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.obtainMessage(ProgressDialog.DISMISS_PROGRESS_DIALOG).sendToTarget();
            progressDialog = null;
        }
    }
}
