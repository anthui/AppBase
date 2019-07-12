package com.ant.utils.file;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.content.FileProvider;

import com.ant.base.config.AppConfig;
import com.ant.utils.SdCardTool;

import java.io.File;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/10/15.
 * describe：
 */
public class FileUtil {
    private static String IMG_DIR = "";

    public static Uri buildUri(Context mContext, String fileName) {


        if (SdCardTool.isMounted()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                return FileProvider.getUriForFile(mContext, AppConfig.getAuthority(mContext), new File(Environment.getExternalStorageDirectory(), fileName));
            }

            return Uri.fromFile(Environment.getExternalStorageDirectory()).buildUpon().appendPath(fileName).build();
        } else {


            return Uri.fromFile(Environment.getDownloadCacheDirectory()).buildUpon().appendPath(fileName).build();
        }
    }

    /**
     * 获取文件 目录
     */
    public static String getPath() {
        String path;
        if (SdCardTool.isMounted()) {
            path = FileConfig.IMG_BASE_SD_DIR;
        } else {
            path = FileConfig.IMG_BASE_CACHE_DIR;
        }
        File file = new File(path);

        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            }
        } else {
            file.mkdirs();
        }

        return path;
    }

    /**
     * 图片压手
     */
    public static <T> void withLs(Context mContext, int maxSize, final List<T> photos, final OnCompressListener onRenameListener) {

        Luban.with(mContext)
                .load(photos)
                .ignoreBy(maxSize)
                .setTargetDir(getPath())
                .setFocusAlpha(true)
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            md.update(filePath.getBytes());
                            String s = new BigInteger(1, md.digest()).toString(32);
                            return s + ".png";
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        return ".png";
                    }
                })
                .setCompressListener(onRenameListener).launch();
    }

    /**
     * 图片压手
     */
    public static <T> void withLs(Context mContext, int maxSize, File photos, final OnCompressListener onRenameListener) {

        Luban.with(mContext)
                .load(photos)
                .ignoreBy(maxSize)
                .setTargetDir(getPath())
                .setFocusAlpha(true)
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        try {
                            MessageDigest md = MessageDigest.getInstance("MD5");
                            md.update(filePath.getBytes());
                            String s = new BigInteger(1, md.digest()).toString(32);
                            return s + ".png";
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        return ".png";
                    }
                })
                .setCompressListener(onRenameListener).launch();
    }
}
