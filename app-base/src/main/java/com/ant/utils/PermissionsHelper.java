package com.ant.utils;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/10/16.
 * describe：
 */

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.ant.dialog.PermissionDialog;

import java.util.ArrayList;
import java.util.List;


/**
 * 权限工具类
 */

public class PermissionsHelper {

    private final int mRequestCode = 100;//权限请求码
    private final int mAPKInstallsCode = 200;//安装权限

    public boolean showSystemSetting = true;

    AppCompatActivity mActivity;

    public PermissionsHelper(AppCompatActivity mActivity) {
        this.mActivity = mActivity;
    }

    private IPermissionsResult mPermissionsResult;

//    public static PermissionsHelper getInstance() {
//        if (permissionsUtils == null) {
//            permissionsUtils = new PermissionsHelper();
//        }
//        return permissionsUtils;
//    }

    public void checkApkPermissions(@NonNull IPermissionsResult permissionsResult) {
        mPermissionsResult = permissionsResult;


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean b = mActivity.getPackageManager().canRequestPackageInstalls();
            if (!b) {
                LogUtil.e("==============================000");
                //  ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES}, mAPKInstallsCode);

                ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.REQUEST_INSTALL_PACKAGES},
                        mAPKInstallsCode);


                //       return;
            }

        }


        permissionsResult.passPermissons();


    }

    public void chekPermissions(String[] permissions, @NonNull IPermissionsResult permissionsResult) {
        mPermissionsResult = permissionsResult;

        if (Build.VERSION.SDK_INT < 23) {//6.0才用动态权限
            permissionsResult.passPermissons();
            return;
        }

        //创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
        List<String> mPermissionList = new ArrayList<>();
        //逐个判断你要的权限是否已经通过
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(mActivity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }

        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(mActivity, permissions, mRequestCode);
        } else {
            //说明权限都已经通过，可以做你想做的事情去
            permissionsResult.passPermissons();
            return;
        }


    }

    //请求权限后回调的方法
    //参数： requestCode  是我们自己定义的权限请求码
    //参数： permissions  是我们请求的权限名称数组
    //参数： grantResults 是我们在弹出页面后是否允许权限的标识数组，数组的长度对应的是权限名称数组的长度，数组的数据0表示允许权限，-1表示我们点击了禁止权限

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        //   boolean hasPermissionDismiss = false;//有权限没有通过
        //未通过的权限
        String permission = "";
//        LogUtil.e("=====================================0000000000000000000");

//        LogUtil.e("==" + requestCode + "            " + permissions.toString());
        if (mRequestCode == requestCode || requestCode == mAPKInstallsCode) {

//            LogUtil.e("ssssssssssssss====" + permissions.toString());
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    // hasPermissionDismiss = true;
                    permission = permissions[i];
                    break;
                }
            }
//            LogUtil.e("============dddd========" + permissions.toString());
            //如果有权限没有被允许
            if (!StringUtil.isEmpty(permission)) {
                if (showSystemSetting) {
                    //被拒绝的是否为apk安装权限
                    //   showSystemPermissionsSettingDialog(permission);//跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
                    //  mPermissionsResult.passPermissons();

                } else {
                    mPermissionsResult.forbitPermissons();
                }
            } else {
                //全部权限通过，可以进行下一步操作。。。
                mPermissionsResult.passPermissons();
            }
        }

    }


    /**
     * 不再提示权限时的展示对话框
     */
    // AlertDialog mPermissionDialog;

    PermissionDialog permissionDialog;

    //未通过的权限
    private void showSystemPermissionsSettingDialog(String permissions) {
        final String mPackName = mActivity.getPackageName();

        //未通过的权限
        if (permissionDialog == null) {
            permissionDialog = new PermissionDialog();
            permissionDialog.setOnClick(new PermissionDialog.OnClick() {
                @Override
                public void onItemClick(boolean isOk) {
                    mPermissionsResult.forbitPermissons();
                }
            });
        }

        permissionDialog.setMessage(permissions);
        permissionDialog.show(mActivity.getSupportFragmentManager());

//        if (mPermissionDialog == null) {
//            mPermissionDialog = new AlertDialog.Builder(mActivity)
//                    .setMessage("已禁用相关权限，请手动授予")
//                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            cancelPermissionDialog();
//
//                            if (isApkInstall) {
//                                Uri packageURI = Uri.parse("package:" + mActivity.getPackageName());
//                                Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
//                                mActivity.startActivity(intent);
//                                return;
//                            }
//
//                            Uri packageURI = Uri.parse("package:" + mPackName);
//                            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
//                            mActivity.startActivity(intent);
//                            mActivity.finish();
//                        }
//                    })
//                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            //关闭页面或者做其他操作
//                            cancelPermissionDialog();
//                            //mContext.finish();
//                            mPermissionsResult.forbitPermissons();
//                        }
//                    })
//                    .create();
//        }
//        mPermissionDialog.show();
    }

    //关闭对话框
//    private void cancelPermissionDialog() {
//        if (mPermissionDialog != null) {
//            mPermissionDialog.cancel();
//            mPermissionDialog = null;
//        }
//
//    }


    public interface IPermissionsResult {
        void passPermissons();

        void forbitPermissons();
    }


}

