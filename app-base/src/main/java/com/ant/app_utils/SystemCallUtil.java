package com.ant.app_utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;

/**
 * <p>Title:  调用系统相应功能    </p>
 * <p>Description:
 * 1.调用系统的相册
 * 2.调用系统的照相机
 * 3.弹出对话框选择相机或者相册
 * 4.调用系统短信
 * 5.调用系统电话
 * 6.调用系统图片裁剪
 * 7.调用系统的分享
 * 8.刷新相册
 * </p>
 * <p>@author: zhangws                 </p>
 * <p>Copyright: Copyright (c) 2012    </p>
 * <p>Company: ffcs Co., Ltd.          </p>
 * <p>Create Time: 2012-8-22           </p>
 * <p>@author:                         </p>
 * <p>Update Time:                     </p>
 * <p>Updater:                         </p>
 * <p>Update Comments:                 </p>
 */
public class SystemCallUtil {

    /**
     * 调用相册回调值,版本低于4.4
     */
    public final static int REQUESTCODE_PHOTOALBUM = 2001;

    /**
     * 调用相册回调值,版本高于4.4
     */
    public final static int REQUESTCODE_PHOTOALBUM_H = 2001;

    /**
     * 调用相机回调值
     */
    public final static int REQUESTCODE_CAMERA = 2002;

    /**
     * 图片裁剪回调值
     */
    public final static int REQUESTCODE_IMAGECUT = 2003;

    /**
     * 保存拍照照片的uri
     */
    public static Uri IMAGE_URI;

    /**
     * 图片全称
     */
    public static String FILE_FULL_PATH;

    /**
     * 调用系统相册
     *
     * @param activity
     */
    public static void photoAlbum(Activity activity) {
        Intent getImage = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(getImage, REQUESTCODE_PHOTOALBUM);
    }

    /**
     * 调用系统相机
     *
     * @param activity
     */
    public static void camera(Activity activity, String filePath) {
        if (!SdCardTool.isMounted()) {
            Toast.makeText(activity, "请确认SD卡", Toast.LENGTH_SHORT).show();
            return;
        }
        Long time = System.currentTimeMillis();
        File f = new File(filePath);
        if (!f.exists()) {
            f.mkdirs();
        }
        String fileFullPath = filePath + "/" + time + ".jpg";
        FILE_FULL_PATH = fileFullPath;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(fileFullPath);
        IMAGE_URI = Uri.fromFile(photo);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, IMAGE_URI);
        activity.startActivityForResult(intent, REQUESTCODE_CAMERA);
    }

    /**
     * 选择相册还是拍照
     * @param activity
     */
    /*public static void showSelect(final Activity activity, final String filePath) {
        final CharSequence[] picItems = { "本地图片", "拍照" };
		AlertDialog dlg = new AlertDialog.Builder(activity).setTitle("选择图片类型")
				.setItems(picItems, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int picItem) {
						if (picItem == 0) {
							photoAlbum(activity);
						} else {
							try {
								camera(activity, filePath);
							} catch (Exception e) {
								Log.e("Exception:" + e);
							}
						}
					}
				}).create();
		dlg.show();
	}
*/

    /**
     * 调用系统短信程序
     *
     * @param context
     */
    public static void sendSMS(Context context) {
        Uri uri = Uri.parse("smsto:");
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        context.startActivity(i);
    }

    /**
     * 调用系统短信程序
     *
     * @param context
     * @param phoneNum 号码
     */
    public static void sendSMS(Context context, String phoneNum) {
        Uri uri = Uri.parse("smsto:" + phoneNum + "");
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        context.startActivity(i);
    }

    /**
     * 调用系统短信程序
     *
     * @param context
     * @param phoneNum 号码
     * @param content  内容
     */
    public static void sendSMS(Context context, String phoneNum, String content) {
        Uri uri = Uri.parse("smsto:" + phoneNum + "");
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.putExtra("sms_body", content);
        context.startActivity(i);
    }

    /**
     * 调用系统电话程序
     *
     * @param context
     * @param phoneNum 电话号码
     */
    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 图片裁剪（适用于大图裁剪）
     *
     * @param activity
     * @param uri
     * @param fileUri  裁剪之后图片保存文件的uri
     */
    public static void ImageCut(Activity activity, Uri uri, Uri fileUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        activity.startActivityForResult(intent, REQUESTCODE_IMAGECUT);
    }

    /**
     * 图片裁剪（适用于大图裁剪）
     *
     * @param activity
     * @param uri
     * @param fileUri  裁剪之后图片保存文件的uri
     */
    public static void ImageCut(Activity activity, Uri uri, Uri fileUri, int aspectX, int aspectY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("aspectX", aspectX);//裁剪框比例
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", aspectX);//输出图片大小
        intent.putExtra("outputY", aspectY);
        intent.putExtra("crop", "true");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        activity.startActivityForResult(intent, REQUESTCODE_IMAGECUT);
    }

    /**
     * 图片裁剪
     *
     * @param activity
     * @param uri      需要裁剪图片的URI
     * @param outputX  输出图像的宽
     * @param outputY  输出图像的高
     */
    public static void ImageCut(Activity activity, Uri uri, int outputX, int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("aspectX", 1);//裁剪框比例
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", outputX);//输出图片大小
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, REQUESTCODE_IMAGECUT);
    }

    /**
     * 图片裁剪
     *
     * @param activity
     * @param uri      需要裁剪图片的URI
     * @param aspectX  裁剪框的比例宽
     * @param aspectY  裁剪框的比例高
     * @param outputX  输出图像的宽
     * @param outputY  输出图像的高
     */
    public static void ImageCut(Activity activity, Uri uri, int aspectX, int aspectY, int outputX,
                                int outputY) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("aspectX", aspectX);//裁剪框比例
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);//输出图片大小
        intent.putExtra("outputY", outputY);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, REQUESTCODE_IMAGECUT);
    }

    /**
     * 调用系统的分享
     *
     * @param
     * @param shareTitle   分享标题
     * @param shareContent 分享内容
     */
    public static void systemShare(Activity activity, String shareTitle, String shareContent) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, shareTitle);
        intent.putExtra(Intent.EXTRA_TEXT, shareContent);
        activity.startActivity(Intent.createChooser(intent, "分享方式选择"));
    }

    /**
     * 刷新相册
     *
     * @param fullFileName
     */
    public static void refreshPhoto(Context context, String fullFileName) {
        Uri localUri = Uri.fromFile(new File(fullFileName));
        Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
        context.sendBroadcast(localIntent);
    }

    /**
     * 播放系统铃声
     */
    public static void playSystemRingtone(Context mContext) {
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone rt = RingtoneManager.getRingtone(mContext, uri);
        rt.play();

    }
}
