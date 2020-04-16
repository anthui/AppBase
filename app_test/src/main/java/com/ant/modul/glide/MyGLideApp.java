package com.ant.modul.glide;

import android.content.Context;

import androidx.annotation.NonNull;

import com.ant.modul.glide.resouce.OkHttpUrlLoader;
import com.ant.modul.glide.resouce.OkHttpUrlLoader2;
import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/8.
 * describe：
 */

@GlideModule
public class MyGLideApp extends AppGlideModule {

    @Override
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder builder) {
        int memoryCacheSizeBytes = 1024 * 1024 * 20; // 20mb
        int diskCacheSizeBytes = 1024 * 1024 * 100;  //100 MB
        builder.setMemoryCache(new LruResourceCache(memoryCacheSizeBytes))
                .setDiskCache(new InternalCacheDiskCacheFactory(context, diskCacheSizeBytes));
    }

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
        super.registerComponents(context, glide, registry);
//        registry.append(String.class, InputStream.class, new DataUrlLoader.StreamFactory<String>());
//        registry.append(Photo.class, InputStream.class, new PhotoModelLoader.Factory());


//          ByteBufferAnimationDecoder byteBufferAnimationDecoder = new ByteBufferAnimationDecoder(glide.getBitmapPool());
        //   registry.append(InputStream.class, Bitmap.class, byteBufferAnimationDecoder);
//        registry.prepend(Photo.class, Bitmap.class, new GalleryDecoder(context, glide.getBitmapPool()));
//        registry.append(Photo.class, InputStream.class, new OkHttpUrlLoader.Factory());
        registry.prepend(Photo.class, InputStream.class, new OkHttpUrlLoader.Factory());
        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader2.Factory());
//        registry.replace(GlideUrl.class, InputStream.class, new com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.Factory());


//        HttpUriLoader.Factory
    }
}
