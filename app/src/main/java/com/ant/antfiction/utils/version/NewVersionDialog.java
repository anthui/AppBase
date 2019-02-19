package com.ant.antfiction.utils.version;


import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ant.antfiction.R;
import com.ant.base.db.SpManager;
import com.ant.base.nicedialog.BaseDialog;
import com.ant.base.version.DownLoadService;
import com.ant.utils.LogUtil;
import com.ant.utils.PermissionsHelper;
import com.ant.utils.StringUtil;
import com.ant.utils.ToastUtil;
import com.ant.views.ProgressButton;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

import static android.content.Context.BIND_AUTO_CREATE;
import static butterknife.internal.Utils.arrayOf;


public class NewVersionDialog extends BaseDialog {
    @BindView(R.id.tv_dissmiss)
    TextView tvDissmiss;

    @BindView(R.id.layout)
    RelativeLayout layout;
    @BindView(R.id.tv_num)
    TextView tvNum;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.btn_update)
    ProgressButton btnUpdate;


    VersionBean bean;
    private VersionBean data;

    PermissionsHelper permissionsHelper;

    public void setTextView(VersionBean bean) {
        if (bean == null) {
            return;
        }
        this.bean = bean;
//        tvNum.setText("当前版本V" + AppHelper.getVersionName(mContext));
//        tvContent.setText("为了更好体验，需要将版本升级至最新版本V" + bean.getAndroidApkVersion());
//
        tvNum.setText("最新版本V" + bean.getVersion());
        String androidApkNote = bean.getContent();
        if (StringUtil.isEmpty(androidApkNote)) {

            tvContent.setText("为了更好体验，需要将版本升级至最新版本");
        } else {

            tvContent.setText(androidApkNote);
        }
    }

    public void checkPermission() {
        permissionsHelper.chekPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionsHelper.IPermissionsResult() {
            @Override
            public void passPermissons() {
                installApk();
            }

            @Override
            public void forbitPermissons() {

            }
        });


    }


    public void installApk() {

        String url = bean.getUrl();
        if (!StringUtil.isEmpty(url) && url.endsWith(".html")) {
            Uri uri = Uri.parse(url);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
            startActivity(intent);
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            boolean b = mActivity.getPackageManager().canRequestPackageInstalls();
            if (!b) {
                LogUtil.e("==============================000");
                //8.0后安装需要 手动允许权限
                ActivityCompat.requestPermissions(mActivity, arrayOf(Manifest.permission.REQUEST_INSTALL_PACKAGES),
                        200);
            }
        }
        removeOldApk();
        ToastUtil.showToast(mContext, "后台更新中,请稍后");
        Intent intent = new Intent(mActivity, DownLoadService.class);
        intent.putExtra(DownLoadService.BUNDLE_KEY_DOWNLOAD_URL, bean.getUrl());
        isBindService = mActivity.bindService(intent, conn, BIND_AUTO_CREATE);


    }

    long mExitTime;


    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(mContext, R.string.str_exit, Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            mActivity.finish();
            System.exit(0);
        }
    }

//    public void exit() {
//        if ((System.currentTimeMillis() - mExitTime) > 2000) {
//            Toast.makeText(mContext, "再按一次退出", Toast.LENGTH_SHORT).show();
//            mExitTime = System.currentTimeMillis();
//        } else {
//
//            mActivity.exitApp();
//        }
//    }


    private boolean isBindService;

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            LogUtil.e("onServiceConnected==============================================");
            DownLoadService.DownloadBinder binder = (DownLoadService.DownloadBinder) service;
            DownLoadService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownLoadService.OnProgressListener() {
                @Override
                public void onProgress(float fraction) {
                    LogUtil.e("下载进度：" + fraction);
                    btnUpdate.setProgress((int) (fraction * 100));

                    btnUpdate.setBackgroundColor(Color.parseColor("#C6C6C6"));

//e
//                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                    if (fraction == DownLoadService.UNBIND_SERVICE && isBindService) {
                        btnUpdate.setProgress(100);
                        btnUpdate.setBackgroundColor(Color.parseColor("#ff8d64"));
                        mActivity.unbindService(conn);
                        isBindService = false;
                        LogUtil.e("onProgress+下载完成");
                        //  MToast.shortToast("下载完成！");
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * 删除上次更新存储在本地的apk
     */
    private void removeOldApk() {
        //获取老ＡＰＫ的存储路径

        File fileName = new File(SpManager.getApkPath(mContext));
        //   LogUtil.e("老APK的存储路径 =" + SPUtil.getString(Constant.SP_DOWNLOAD_PATH, ""));

        if (fileName != null && fileName.exists() && fileName.isFile()) {
            fileName.delete();
            LogUtil.e("存储器内存在老APK，进行删除操作");
        }
    }

    @Override
    public int getMainContentViewId() {
        return R.layout.dialog_version;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initComponents(View mView) {
        setTextView(bean);

//        permissionsHelper = new PermissionsHelper(mActivity);
    }


    @OnClick({R.id.tv_dissmiss, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_dissmiss:

                if (bean != null) {
                    if (bean.getUpdate() == 1) {
                        exit();
                    } else {
                        dismiss();
                    }
                } else {
                    dismiss();
                }
                break;
            case R.id.btn_update:
                checkPermission();
                break;
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setOutCancel(false);
    }

    public void setData(VersionBean data, PermissionsHelper permissionsHelper) {
        this.bean = data;

        this.permissionsHelper = permissionsHelper;
    }

}
