package com.ant.utils.file;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import com.ant.base.BaseActivity;
import com.ant.utils.LogUtil;
import com.ant.utils.PermissionsHelper;
import com.ant.utils.StringUtil;
import com.ant.utils.ToastUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/10/15.
 * describe：
 */
public class PhotoHelper {
    public static final int REQUEST_IMAGE_GET = 0;
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_SMALL_IMAGE_CUTTING = 2;
    // private static final int REQUEST_CHANGE_USER_NICK_NAME = 10;
    public static final String IMAGE_FILE_NAME = "user_head_icon.jpg";

    private String TAG = "msg";
    BaseActivity mActivity;
    private OnPhotoResultListener onPhotoResultListener;

    public PhotoHelper(BaseActivity activity) {
        this.mActivity = activity;
    }

    public void photoSelect() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 判断系统中是否有处理该 Intent 的 Activity
        if (intent.resolveActivity(mActivity.getPackageManager()) != null) {
            mActivity.startActivityForResult(intent, REQUEST_IMAGE_GET);
        } else {
            Toast.makeText(mActivity, "未找到图片查看器", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 拍照
     */

    public void imageCapture() {

        //  takePhoto("");
    }

    /**
     * 拍照
     */

    public void takePhoto(PermissionsHelper permissionsUtils, final String filePath) {

        String[] permissions = new String[]{Manifest.permission.CAMERA};

        permissionsUtils.chekPermissions(permissions, new PermissionsHelper.IPermissionsResult() {
            @Override
            public void passPermissons() {

                imageCapture(filePath);
            }

            @Override
            public void forbitPermissons() {


                ToastUtil.showToast(mActivity, "请先允许 获取读取手机状态权限");

                // finish();
            }
        });
    }


    private void imageCapture(String filePath) {


        if (StringUtil.isEmpty(filePath)) {
            filePath = IMAGE_FILE_NAME;
        }

        Intent intent;
        Uri pictureUri;
        //getMyPetRootDirectory()得到的是Environment.getExternalStorageDirectory() + File.separator+"MyPet"
        //也就是我之前创建的存放头像的文件夹（目录）
        File pictureFile = new File(PictureUtil.getMyPetRootDirectory(), filePath);

        if (pictureFile.exists() && pictureFile.isFile()) {
            boolean delete = pictureFile.delete();
            LogUtil.e("删除成功" + delete);
        }
        // 判断当前系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //这一句非常重要
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            //""中的内容是随意的，但最好用package名.provider名的形式，清晰明了
            pictureUri = FileProvider.getUriForFile(mActivity,
                    mActivity.getPackageName() + ".fileProvider", pictureFile);
        } else

        {
            intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            pictureUri = Uri.fromFile(pictureFile);
        }
        // 去拍照,拍照的结果存到oictureUri对应的路径中
        intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
        Log.e(TAG, "before take photo" + pictureUri.toString());
        mActivity.startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }


    public void setPicToView(Uri uri) {
        if (uri != null) {
            Bitmap photo = null;
            try {
                photo = BitmapFactory.decodeStream(mActivity.getContentResolver().openInputStream(uri));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // 创建 smallIcon 文件夹
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //String storage = Environment.getExternalStorageDirectory().getPath();
                File dirFile = new File(PictureUtil.getMyPetRootDirectory(), "Icon");
                if (!dirFile.exists()) {
                    if (!dirFile.mkdirs()) {
                        Log.d(TAG, "in setPicToView->文件夹创建失败");
                    } else {
                        Log.d(TAG, "in setPicToView->文件夹创建成功");
                    }
                }
                File file = new File(dirFile, IMAGE_FILE_NAME);
                //   InfoPrefs.setData(Constants.UserInfo.HEAD_IMAGE, file.getPath());
                //Log.d("result",file.getPath());
                // Log.d("result",file.getAbsolutePath());
                // 保存图片
                FileOutputStream outputStream = null;
                try {
                    outputStream = new FileOutputStream(file);
                    photo.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                    outputStream.flush();
                    outputStream.close();

                    if (onPhotoResultListener != null) {

                        onPhotoResultListener.onPhotoResult(file);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 在视图中显示图片
            // mActivity.showHeadImage();
            //circleImageView_user_head.setImageBitmap(InfoPrefs.getData(Constants.UserInfo.GEAD_IMAGE));
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {


        // 回调成功
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {

                // 切割返回
                case REQUEST_SMALL_IMAGE_CUTTING:
                    Log.e(TAG, "before show");
                    File cropFile = new File(PictureUtil.getMyPetRootDirectory(), "crop.jpg");
                    Uri cropUri = Uri.fromFile(cropFile);
                    setPicToView(cropUri);
                    break;

                // 相册选取  返回
                case REQUEST_IMAGE_GET:
                    Uri uri = PictureUtil.getImageUri(mActivity, data);
                    startPhotoZoom(uri);
                    break;

                // 拍照 返回
                case REQUEST_IMAGE_CAPTURE:

                    File pictureFile = new File(PictureUtil.getMyPetRootDirectory(), IMAGE_FILE_NAME);
                    Uri pictureUri;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        pictureUri = FileProvider.getUriForFile(mActivity,
                                mActivity.getPackageName() + ".fileProvider", pictureFile);
                        Log.e(TAG, "picURI=" + pictureUri.toString());
                    } else {
                        pictureUri = Uri.fromFile(pictureFile);
                    }
                    startPhotoZoom(pictureUri);
                    break;
                default:
            }
        } else {
            Log.e(TAG, "result = " + resultCode + ",request = " + requestCode);
        }
    }

    private void startPhotoZoom(Uri uri) {
        Log.d(TAG, "Uri = " + uri.toString());


        //保存裁剪后的图片
        File cropFile = new File(PictureUtil.getMyPetRootDirectory(), "crop.jpg");
        try {
            if (cropFile.exists()) {
                cropFile.delete();
                Log.e(TAG, "delete");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Uri cropUri;
        cropUri = Uri.fromFile(cropFile);

        Intent intent = new Intent("com.android.camera.action.CROP");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1); // 裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 500); // 输出图片大小
        intent.putExtra("outputY", 500);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", false);

        Log.e(TAG, "cropUri = " + cropUri.toString());

        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropUri);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        mActivity.startActivityForResult(intent, REQUEST_SMALL_IMAGE_CUTTING);
    }

    public interface OnPhotoResultListener {
        void onPhotoResult(File file);

        void onPhotoCancel();

    }


    public void setOnPhotoResultListener(OnPhotoResultListener onPhotoResultListener) {
        this.onPhotoResultListener = onPhotoResultListener;
    }
}
