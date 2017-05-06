package com.skyseraph.xknife.lib.xnet.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

import com.skyseraph.xknife.lib.xnet.subscriber.IProgressListener;

/**
 * Created by SkySeraph on 2015/7/5.
 */
public class ProgressDialog extends Handler {

    /**
     * The constant SHOW_PROGRESS_DIALOG.
     */
    public static final int SHOW_PROGRESS_DIALOG = 0x01;
    /**
     * The constant DISMISS_PROGRESS_DIALOG.
     */
    public static final int DISMISS_PROGRESS_DIALOG = 0x02;

    private android.app.ProgressDialog progressDialog;

    private Context context;
    private boolean cancelable;
    private IProgressListener progressListener;

    /**
     * Instantiates a new Progress dialog.
     *
     * @param context          the context
     * @param progressListener the progress listener
     * @param cancelable       the cancelable
     */
    public ProgressDialog(Context context, IProgressListener progressListener,
                          boolean cancelable) {
        super();
        this.context = context;
        this.progressListener = progressListener;
        this.cancelable = cancelable;
    }

    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new android.app.ProgressDialog(context);

            progressDialog.setCancelable(cancelable);

            if (cancelable) {
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        progressListener.onCancel();
                    }
                });
            }

            if (!progressDialog.isShowing()) {
                progressDialog.show();
            }
        }
    }

    private void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                showProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

}