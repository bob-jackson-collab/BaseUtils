package com.ys.baseproject.properties;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GenericLoaderFactory;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;

import java.io.File;

/**
 * Created by Administrator on 2017/2/22.
 */

public class MyGlideModule implements GlideModule{

    private String path;  //设置缓存的路径

    public static final int FILE_CACHE_SIZE = 100 * 1024 * 1024;

    public static final int MEMORY_CACHE_SIZE = 10 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        path = context.getExternalCacheDir().getAbsolutePath() + "glide_cache";
        final File cacheFile = new File(path);

        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                return DiskLruCacheWrapper.get(cacheFile,FILE_CACHE_SIZE);
            }
        });

        builder.setMemoryCache(new LruResourceCache(MEMORY_CACHE_SIZE));


    }

    @Override
    public void registerComponents(Context context, Glide glide) {
//         glide.register(d.DataMode.class, String.class, new ModelLoaderFactory<d.DataMode, String>() {
//             @Override
//             public ModelLoader<d.DataMode, String> build(Context context, GenericLoaderFactory factories) {
//                 return null;
//             }
//
//             @Override
//             public void teardown() {
//
//             }
//         });
    }
}
