package com.ant.utils.file;


import android.os.Environment;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2017/11/24
 * describe：所有文件目录地址
 */
public class FileConfig {
    //用户头像  本地上传裁剪后的地址
    public static String FILE_BASE_PATH = "/hgqx/img/"; //本路 存储的图片文件夹 sd卡或者内存
    public static String IMG_BASE_SD_DIR = Environment.getExternalStorageDirectory() + FILE_BASE_PATH; //本路 存储的图片文件夹 sd卡或者内存
    public static String IMG_BASE_CACHE_DIR = Environment.getDownloadCacheDirectory() + FILE_BASE_PATH; //本路 存储的图片文件夹 sd卡或者内存


    public static String FILE_SHARE_POSTER = "share_poster.png"; //本路 存储的图片文件夹 sd卡或者内存

}
