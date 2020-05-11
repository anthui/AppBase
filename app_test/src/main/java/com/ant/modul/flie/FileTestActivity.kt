package com.ant.modul.flie

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.annotation.RequiresApi
import com.ant.anttestlibrary.R
import com.ant.anttestlibrary.databinding.FileBind
import com.ant.app_base.BaseBindActivity
import java.io.ByteArrayOutputStream


/**
 * copyright：
 * @author：anthui
 * Version：1.0
 * creation date： 2020/4/21.
 * describe：
 */

class FileTestActivity() : BaseBindActivity<FileBind>() {

    override fun initComponents(savedInstanceState: Bundle?, rootView: View?) {

        dataBinding.tvReadPath.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getFilepath()
            }
        }
        dataBinding.tvReadFile.setOnClickListener {
            readFile()
        }

    }

    override fun initData() {
    }

    override fun getMainContentViewId(): Int {

        return R.layout.activity_file
    }


    fun readFile() {

        var assets = mContext.assets.open("ic_glide.jpg")

        var out = ByteArrayOutputStream()

        var len = 0;
//        val var1 = ByteArray(var0)
        var byte = ByteArray(4)


        while (true) {
            assets.read(byte)


            if (len == -1) {
                break
            }


        }


    }

    /**
     * @author：anthui creation date：2020/4/21
     * describe： 获取文件目录
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getFilepath() {
        var cacheDir = mContext.cacheDir

        var dataDir = mContext.filesDir
        var fileList = mContext.fileList()
        var codeCacheDir = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mContext.codeCacheDir
        } else {
            null
        }
//        var externalCacheDir = mContext.externalCacheDir
//        var externalMediaDirs = mContext.externalMediaDirs


        var absolutePath = Environment.getExternalStorageDirectory().absolutePath
        var externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory("")
        var externalFilesDir = getExternalFilesDir("")


        var databasePath = mContext.databaseList()
        var stringBuilder = StringBuilder()
        stringBuilder.append("\ndatabasePath ====   " + databasePath)
        stringBuilder.append("\ncacheDi'r====   " + cacheDir)
        stringBuilder.append("\ndataDir====   " + dataDir)
        stringBuilder.append("\nfileList====   " + fileList)
        stringBuilder.append("\ncodeCacheDir====   " + codeCacheDir)
        stringBuilder.append("\n\nabsolutePath====   " + absolutePath)
        stringBuilder.append("\nexternalStoragePublicDirectory====   " + externalStoragePublicDirectory)
        stringBuilder.append("\nexternalFilesDir====   " + externalFilesDir)
//

        dataBinding.tvContent.text = stringBuilder.toString()


    }

}
