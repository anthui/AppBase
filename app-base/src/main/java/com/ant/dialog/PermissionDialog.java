package com.ant.dialog;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ant.base.R;
import com.ant.base.config.AppConfig;
import com.ant.base.nicedialog.BaseDialog;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/1/28
 * describe：
 */
public class PermissionDialog extends BaseDialog {


    private TextView tvMessage;

    String perssionMessage = "";

    private void initView() {


        tvMessage = mView.findViewById(R.id.tv_message);
        setTvMessage();
        mView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClick != null) {
                    onClick.onItemClick(false);
                }
                dismiss();

            }
        });

        mView.findViewById(R.id.tv_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();


                if (isApkInstall) {
                    Uri packageURI = Uri.parse("package:" + mActivity.getPackageName());
                    Intent intent = new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES, packageURI);
                    mActivity.startActivity(intent);
                } else {
                    Uri packageURI = Uri.parse("package:" + mActivity.getPackageName());
                    Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
                    mActivity.startActivity(intent);
                }

                onClick.onItemClick(true);
            }
        });
//
    }


    @Override
    public int getMainContentViewId() {
        return R.layout.dialog_perssion;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setOutCancel(false);
    }

    @Override
    protected void initComponents(View mView) {
        initView();
    }

    //这是权限单独判断
    public void setMessage(String permission) {
        isApkInstall = false;

        switch (permission) {
            //应用安装权限
            case Manifest.permission.REQUEST_INSTALL_PACKAGES:
                perssionMessage = "需要未知应用权限，您是否手动允许?\n具体包括：安装许新版本";
                isApkInstall = true;

                break;
            //读取手机状态
            case Manifest.permission.READ_PHONE_STATE:
                perssionMessage = "需要使用电话权限,您是否手动允许？\n具体包括：读取本机识别码";

                break;
            //读取sd权限
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                perssionMessage = "需要使用存储权限,您是否手动允许？\n具体包括：SD卡文件存储";

                break;

            case Manifest.permission.CAMERA:
                perssionMessage = "需要使用相机权限,您是否手动允许？\n具体包括：二维码扫描";
                break;
            //   infoDialog.setMsg(activity.getString(R.string.app_name) + " 需要使用电话权限,您是否手动允许？\n具体包括：读取本机识别码等权限。");

        }
        setTvMessage();
    }

    boolean isApkInstall = false;

    public void setTvMessage() {
        if (tvMessage != null && mActivity != null) {
            tvMessage.setText(AppConfig.getAppName(mContext) + perssionMessage);
        }
    }


    OnClick onClick;

    public void setOnClick(OnClick onClick) {
        this.onClick = onClick;
    }

    public interface OnClick

    {
        void onItemClick(boolean isOk);
    }
}
