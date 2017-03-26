package com.skyseraph.xknife.lib.ximage.api;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by SkySeraph on 2016/8/8.
 */

public abstract class XImageLoadListener<T, K> {

    /**
     * 图片加载成功回调
     *
     * @param uri      图片url 或资源id 或 文件
     * @param view     目标载体，不传则为空
     * @param resource 返回的资源,GlideDrawable或者Bitmap或者GifDrawable,ImageView.setImageRecourse设置
     */
    public abstract void onLoadingComplete(T uri, ImageView view, K resource);

    /**
     * 图片加载异常返回
     *
     * @param source 图片地址、File、资源id
     * @param e      异常信息
     */
    public abstract void onLoadingError(T source, Exception e);

    /**
     * 加载开始(Option)
     *
     * @param source      图片来源
     * @param placeHolder 开始加载占位图
     */
    public void onLoadingStart(T source, Drawable placeHolder) {
    }
}
