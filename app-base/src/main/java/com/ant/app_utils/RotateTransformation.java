package com.ant.app_utils;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import androidx.annotation.NonNull;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

import java.security.MessageDigest;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/1/19.
 * describe：
 */
public class RotateTransformation extends BitmapTransformation {

    //    public RotateTransformation(Context context) {
//        super(context);
//    }
//
//
    private float rotateRotationAngle = 0f;

    public RotateTransformation(float rotateRotationAngle) {
        this.rotateRotationAngle = rotateRotationAngle;
    }

    //
    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle);


        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }
//
//    @Override
//    public String getId() {
//        return "rotate" + rotateRotationAngle;
//    }


    @Override
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {

    }

//    @Override
//    protected Bitmap transform(@NonNull BitmapPool pool, @NonNull Bitmap toTransform, int outWidth, int outHeight) {
//        return null;
//    }
}
