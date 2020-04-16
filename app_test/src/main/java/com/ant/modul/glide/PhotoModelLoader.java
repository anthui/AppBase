package com.ant.modul.glide;

import androidx.annotation.Nullable;

import com.ant.app_utils.LogUtil;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;

import java.io.InputStream;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/14.
 * describe：
 */
public class PhotoModelLoader extends BaseGlideUrlLoader<Photo> {


    public static class Factory implements ModelLoaderFactory<Photo, InputStream> {

        private final ModelCache<Photo, GlideUrl> modelCache = new ModelCache<Photo, GlideUrl>(500);

        @Override
        public ModelLoader<Photo, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new PhotoModelLoader(multiFactory.build(GlideUrl.class, InputStream.class),
                    modelCache);
        }

        @Override
        public void teardown() {
        }
    }

    protected PhotoModelLoader(ModelLoader<GlideUrl, InputStream> concreteLoader) {
        super(concreteLoader);
    }

    protected PhotoModelLoader(ModelLoader<GlideUrl, InputStream> concreteLoader, @Nullable ModelCache<Photo, GlideUrl> modelCache) {
        super(concreteLoader, modelCache);
    }

    @Override
    protected String getUrl(Photo photo, int width, int height, Options options) {


//        LogUtil.e("msg=================== " + photo.getUrl());
        return "";
    }

    @Override
    public boolean handles(Photo photo) {
        return true;
    }

}