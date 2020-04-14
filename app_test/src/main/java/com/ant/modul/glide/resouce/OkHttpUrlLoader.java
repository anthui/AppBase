package com.ant.modul.glide.resouce;

import com.ant.modul.glide.Photo;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelLoader;
import com.bumptech.glide.load.model.ModelLoaderFactory;
import com.bumptech.glide.load.model.MultiModelLoaderFactory;

import java.io.InputStream;

import okhttp3.Call;
import okhttp3.OkHttpClient;

/**
 * A simple model loader for fetching media over http/https using OkHttp.
 */
public class OkHttpUrlLoader implements ModelLoader<Photo, InputStream> {

    private final Call.Factory client;

    public OkHttpUrlLoader(Call.Factory client) {
        this.client = client;
    }

    @Override
    public boolean handles(Photo url) {
        return true;
    }

    @Override
    public LoadData<InputStream> buildLoadData(Photo model, int width, int height,
                                               Options options) {

        GlideUrl glideUrl = new GlideUrl(model.getUrl());
        return new LoadData<>(glideUrl, new OkHttpStreamFetcher(client, glideUrl));
    }

    /**
     * The default factory for {@link OkHttpUrlLoader}s.
     */
    public static class Factory implements ModelLoaderFactory<Photo, InputStream> {
        private static volatile Call.Factory internalClient;
        private Call.Factory client;

        private static Call.Factory getInternalClient() {
            if (internalClient == null) {
                synchronized (Factory.class) {
                    if (internalClient == null) {
                        internalClient = new OkHttpClient();
                    }
                }
            }
            return internalClient;
        }

        /**
         * Constructor for a new Factory that runs requests using a static singleton client.
         */
        public Factory() {
            this(getInternalClient());
        }

        /**
         * Constructor for a new Factory that runs requests using given client.
         *
         * @param client this is typically an instance of {@code OkHttpClient}.
         */
        public Factory(Call.Factory client) {
            this.client = client;
        }

        @Override
        public ModelLoader<Photo, InputStream> build(MultiModelLoaderFactory multiFactory) {
            return new OkHttpUrlLoader(client);
        }

        @Override
        public void teardown() {
            // Do nothing, this instance doesn't own the client.
        }
    }
}
