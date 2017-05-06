package com.skyseraph.xknife.lib.widge.module.loading;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.skyseraph.xknife.lib.widge.R;


/**
 * Created by SkySeraph on 2015/3/18.
 * 通用加载对话框
 * Use:
 * LoadingDialog.with(this).show();
 * LoadingDialog.with(this).setCanceled(false).show();
 * LoadingDialog.with(this).setMessage("正在加载中...").show();
 */
public class LoadingDialog extends Dialog {

    private static LoadingDialog dialog;
    private Context context;
    private TextView logingMessage;

    /**
     * Instantiates a new Loading dialog.
     *
     * @param context the context
     */
    public LoadingDialog(Context context) {
        super(context, R.style.loading_dialog);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        logingMessage = (TextView) findViewById(R.id.tv_loading_message);
    }

    /**
     * With loading dialog.
     *
     * @param context the context
     * @return the loading dialog
     */
    public static LoadingDialog with(Context context) {
        if (dialog == null) {
            dialog = new LoadingDialog(context);
        }
        return dialog;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (dialog != null)
            dialog = null;
    }

    /**
     * Sets canceled.
     *
     * @param cancel the cancel
     * @return the canceled
     */
    public LoadingDialog setCanceled(boolean cancel) {
        setCanceledOnTouchOutside(cancel);
        setCancelable(cancel);
        return dialog;
    }

    /**
     * Sets message.
     *
     * @param message the message
     * @return the message
     */
    public LoadingDialog setMessage(String message) {
        if (logingMessage != null && !TextUtils.isEmpty(message)) {
            logingMessage.setText(message);
        }
        return this;
    }
}
