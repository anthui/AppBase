package com.ant.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/1/19.
 * describe：
 */
public class RotateTransformation extends BitmapTransformation {

    public RotateTransformation(Context context) {
        super(context);
    }


    private float rotateRotationAngle = 0f;

    public RotateTransformation(Context context, float rotateRotationAngle) {
        super(context);

        this.rotateRotationAngle = rotateRotationAngle;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        Matrix matrix = new Matrix();

        matrix.postRotate(rotateRotationAngle);

        return Bitmap.createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
    }

    @Override
    public String getId() {
        return "rotate" + rotateRotationAngle;
    }


}
