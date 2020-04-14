package com.ant.modul.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.ant.anttestlibrary.R
import com.ant.app_utils.LogUtil
import com.bumptech.glide.load.Option
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapResource


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/14.
 * describe：
 */
open class GalleryDecoder(
        private val context: Context,
        private val bitmapPool: BitmapPool
) : ResourceDecoder<Photo, Bitmap> {


    override fun decode(source: Photo, width: Int, height: Int, options: Options): Resource<Bitmap>? {


        LogUtil.e("msg===========================" + source)
        return BitmapResource.obtain(BitmapFactory.decodeResource(context.resources, R.mipmap.bf_load2), bitmapPool)
    }

    companion object {
        val GALLERY_DECODER: Option<Boolean> = Option.memory("abc")
    }

    override fun handles(source: Photo, options: Options): Boolean {
//        = options.get(GALLERY_DECODER)

//                ?: false

        LogUtil.e("msg====================================")
        return true;
    }

}