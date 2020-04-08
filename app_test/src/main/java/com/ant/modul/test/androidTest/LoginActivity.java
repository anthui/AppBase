package com.ant.modul.test.androidTest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseActivity;
import com.ant.app_utils.ToastUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/30.
 * describe：
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText mNameEdit;
    private EditText mPasswordEdit;
    public Button mLoginBtn;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_login;
    }

    private void initView() {
        mNameEdit = (EditText) findViewById(R.id.edit_name);
        mPasswordEdit = (EditText) findViewById(R.id.edit_password);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
//                login();
                Intent intent = new Intent(mActivity, SecondActivity.class);
                intent.putExtra("id", "123");
                startActivity(intent);

                break;
            default:
                break;
        }
    }

    private void login() {
        if (TextUtils.isEmpty(mNameEdit.getText().toString())) {
            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(mPasswordEdit.getText().toString())) {
            Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mPasswordEdit.getText().length() < 6) {
            Toast.makeText(this, "密码长度小于6", Toast.LENGTH_SHORT).show();
            return;
        }
        mLoginBtn.setText("登录成功");
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        initView();
    }


    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确认退出应用吗")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        ToastUtil.showCenterToast(mContext, "点击确认餐牛", true);
//                        finish();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).create().show();
    }
}
