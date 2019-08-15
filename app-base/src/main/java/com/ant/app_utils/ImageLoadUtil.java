package com.ant.app_utils;

import android.content.Context;
import android.widget.ImageView;

import com.ant.app_views.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2017/12/1
 * describe：图片下载工具类，所有的图片下载都用此工具类
 */

public class ImageLoadUtil {

    /**
     * 默认图片加载
     */
    public static void LoadImgNormal(Context context, String imgUrl, ImageView imageView) {

        Glide.with(context).load(imgUrl).centerCrop().priority(Priority.HIGH).into(imageView);


    }

    /**
     * 默认图片，包含本地默认图片
     */
    public static void LoadImgDefault(Context context, int defaultId, String imgUrl, ImageView imageView) {

        Glide.with(context).load(imgUrl).centerCrop().placeholder(defaultId).error(defaultId).priority(Priority.HIGH).into(imageView);
    }


    /**
     * 加载圆形图片，及默认图片
     */
    public static void LoadCircleImgDefault(Context context, int defaultId, String imgUrl, ImageView imageView) {
        Glide.with(context).load(imgUrl).centerCrop().placeholder(defaultId).transform(new GlideCircleTransform()).error(defaultId).priority(Priority.HIGH).into(imageView);

    }

    /**
     * 默认圆形图片加载图片
     */
    public static void LoadCircleImg(Context context, String imgUrl, ImageView imageView) {
        Glide.with(context).load(imgUrl).transform(new GlideCircleTransform()).into(imageView);
    }

    /**
     * 默认圆形图片加载图片
     */
    public static void LoadCircleImg(Context context, File bitmap, ImageView imageView) {
        Glide.with(context).load(bitmap).transform(new GlideCircleTransform()).into(imageView);
    }

    /**
     * 加载gif
     */
    public static void LoadGif(Context context, String gifUrl, ImageView imageView) {


        Glide.with(context).asGif().load(gifUrl).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);


    }


}
