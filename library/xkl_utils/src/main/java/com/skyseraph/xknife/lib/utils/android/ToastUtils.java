package com.skyseraph.xknife.lib.utils.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

/**
 * Created by SkySeraph on 2015/2/19 19:54
 */
public class ToastUtils {

    private ToastUtils() {
        throw new AssertionError();
    }

    private static Toast sToast;
    private static Handler sHandler = new Handler(Looper.getMainLooper());
    private static boolean isJumpWhenMore;

    /**
     * Init.
     *
     * @param isJumpWhenMore the is jump when more
     */
    public static void init(boolean isJumpWhenMore) {
        ToastUtils.isJumpWhenMore = isJumpWhenMore;
    }

    /**
     * Show short toast safe.
     *
     * @param context the context
     * @param text    the text
     */
    public static void showShortToastSafe(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, text, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * Show short toast safe.
     *
     * @param context the context
     * @param resId   the res id
     */
    public static void showShortToastSafe(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, resId, Toast.LENGTH_SHORT);
            }
        });
    }

    /**
     * Show short toast safe.
     *
     * @param context the context
     * @param resId   the res id
     * @param args    the args
     */
    public static void showShortToastSafe(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, resId, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * Show short toast safe.
     *
     * @param context the context
     * @param format  the format
     * @param args    the args
     */
    public static void showShortToastSafe(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, format, Toast.LENGTH_SHORT, args);
            }
        });
    }

    /**
     * Show long toast safe.
     *
     * @param context the context
     * @param text    the text
     */
    public static void showLongToastSafe(final Context context, final CharSequence text) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, text, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * Show long toast safe.
     *
     * @param context the context
     * @param resId   the res id
     */
    public static void showLongToastSafe(final Context context, final int resId) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, resId, Toast.LENGTH_LONG);
            }
        });
    }

    /**
     * Show long toast safe.
     *
     * @param context the context
     * @param resId   the res id
     * @param args    the args
     */
    public static void showLongToastSafe(final Context context, final int resId, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, resId, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * Show long toast safe.
     *
     * @param context the context
     * @param format  the format
     * @param args    the args
     */
    public static void showLongToastSafe(final Context context, final String format, final Object... args) {
        sHandler.post(new Runnable() {
            @Override
            public void run() {
                showToast(context, format, Toast.LENGTH_LONG, args);
            }
        });
    }

    /**
     * Show short toast.
     *
     * @param context the context
     * @param text    the text
     */
    public static void showShortToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_SHORT);
    }

    /**
     * Show short toast.
     *
     * @param context the context
     * @param resId   the res id
     */
    public static void showShortToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    /**
     * Show short toast.
     *
     * @param context the context
     * @param resId   the res id
     * @param args    the args
     */
    public static void showShortToast(Context context, int resId, Object... args) {
        showToast(context, resId, Toast.LENGTH_SHORT, args);
    }

    /**
     * Show short toast.
     *
     * @param context the context
     * @param format  the format
     * @param args    the args
     */
    public static void showShortToast(Context context, String format, Object... args) {
        showToast(context, format, Toast.LENGTH_SHORT, args);
    }

    /**
     * Show long toast.
     *
     * @param context the context
     * @param text    the text
     */
    public static void showLongToast(Context context, CharSequence text) {
        showToast(context, text, Toast.LENGTH_LONG);
    }

    /**
     * Show long toast.
     *
     * @param context the context
     * @param resId   the res id
     */
    public static void showLongToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_LONG);
    }

    /**
     * Show long toast.
     *
     * @param context the context
     * @param resId   the res id
     * @param args    the args
     */
    public static void showLongToast(Context context, int resId, Object... args) {
        showToast(context, resId, Toast.LENGTH_LONG, args);
    }

    /**
     * Show long toast.
     *
     * @param context the context
     * @param format  the format
     * @param args    the args
     */
    public static void showLongToast(Context context, String format, Object... args) {
        showToast(context, format, Toast.LENGTH_LONG, args);
    }

    private static void showToast(Context context, CharSequence text, int duration) {
        if (isJumpWhenMore) cancelToast();
        if (sToast == null) {
            sToast = Toast.makeText(context.getApplicationContext(), text, duration);
        } else {
            sToast.setText(text);
            sToast.setDuration(duration);
        }
        sToast.show();
    }

    private static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getResources().getText(resId).toString(), duration);
    }

    private static void showToast(Context context, int resId, int duration, Object... args) {
        showToast(context, String.format(context.getResources().getString(resId), args), duration);
    }

    private static void showToast(Context context, String format, int duration, Object... args) {
        showToast(context, String.format(format, args), duration);
    }

    /**
     * Cancel toast.
     */
    public static void cancelToast() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

}
