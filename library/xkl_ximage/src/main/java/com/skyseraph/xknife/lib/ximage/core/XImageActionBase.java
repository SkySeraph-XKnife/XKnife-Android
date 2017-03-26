package com.skyseraph.xknife.lib.ximage.core;


import android.content.Context;

import com.skyseraph.xknife.lib.ximage.api.XImageLoadListener;
import com.skyseraph.xknife.lib.ximage.api.XImageView;

/**
 * Created by SkySeraph on 2016/8/8.
 *
 * @param <T> the type parameter
 */
public abstract class XImageActionBase<T> {

    /**
     * 默认加载
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public abstract void loadImage(Context context, XImageView imageView);

    /**
     * 显示加载中占位图片和加载失败图片
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadImageWithHolder(Context context, XImageView imageView) {
    }


    /**
     * 加载缩略图
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadImageWithThumbnail(Context context, XImageView imageView) {
    }

    /**
     * 加载指定大小
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadImageWithSize(Context context, XImageView imageView) {
    }

    /**
     * 加载动画
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadImageWithAnim(Context context, XImageView imageView) {
    }

    /**
     * 缓存策略
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadImageWithCache(Context context, XImageView imageView) {
    }

    /**
     * 加载圆形图片
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadCircleImage(Context context, XImageView imageView) {
    }

    /**
     * 加载模糊图片
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadBlurImage(Context context, XImageView imageView) {
    }


    /**
     * Gif加载
     *
     * @param context   上下文
     * @param imageView the image view
     */
    public void loadGifImage(Context context, XImageView imageView) {
    }

    /**
     * 设置监听请求接口
     *
     * @param context   上下文
     * @param imageView the image view
     * @param listener  the listener
     */
    public void loadImageListener(Context context, XImageView imageView, XImageLoadListener listener) {
    }

    /**
     * 取消所有正在下载或等待下载的任务。
     *
     * @param context 上下文
     */
    protected void cancelAllTasks(Context context) {
    }

    /**
     * 恢复所有任务
     *
     * @param context the context
     */
    protected void resumeAllTasks(Context context) {
    }

    /**
     * 获取缓存大小
     *
     * @param context 上下文
     * @return the cache size
     */
    protected String getCacheSize(Context context) {
        return null;
    }

    /**
     * 清除所有缓存
     *
     * @param context 上下文
     */
    protected void clearAllCache(Context context) {
    }

}
