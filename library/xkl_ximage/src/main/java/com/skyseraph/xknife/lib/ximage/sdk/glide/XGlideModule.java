package com.skyseraph.xknife.lib.ximage.sdk.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by SkySeraph on 2016/8/8.
 * * (1)清单文件注册
 * <application ...>
 * <meta-data
 * android:name="com.mypackage.MyGlideModule"
 * android:value="GlideModule" />
 * </application>
 * (2)混淆
 * -keepnames class com.mypackage.MyGlideModule
 * (3)多个glideModule冲突
 * <meta-data android:name=”com.mypackage.MyGlideModule” tools:node=”remove” />
 */
public class XGlideModule implements GlideModule {

    private String diskCacheName = "XImage";

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //设置图片解码格式
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

        //设置内存缓存大小
        int maxMemory = (int) Runtime.getRuntime().maxMemory();//获取系统分配给应用的总内存大小
        int memoryCacheSize = maxMemory / 8;//设置图片内存缓存占用八分之一
        builder.setMemoryCache(new LruResourceCache(memoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(memoryCacheSize));

        // 存放路径和缓存控件大小
        int diskCacheSize = 1024 * 1024 * 30;
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheName, diskCacheSize));  // data/data/xx/cache/
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, diskCacheName, diskCacheSize));  //存放在外置文件浏览器
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}
