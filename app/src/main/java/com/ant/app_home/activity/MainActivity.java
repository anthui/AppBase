package com.ant.app_home.activity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ant.app_base.BaseActivity;
import com.ant.app_base.dabaBase.table.UserBean;
import com.ant.app_base.user.UserSp;
import com.ant.app_home.R;


public class MainActivity extends BaseActivity {
    private TextView tvContent;

    int nameNum = 1;

    UserBean userBean;

    @Override
    public void initComponents(Bundle savedInstanceState, View view) {

        tvContent = findViewById(R.id.tv_content);
        findViewById(R.id.tv_insert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userBean = new UserBean();
                userBean.setUser_id(System.currentTimeMillis() + "");
                userBean.setUser_nick_name("哈哈");
                UserSp.saveUserBean(userBean);

            }
        });


        findViewById(R.id.tv_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userBean.setUser_nick_name("00000");
                UserSp.saveUserBean(userBean);

            }
        });


        findViewById(R.id.tv_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userBean.setUser_id("aaaaaaaaaaaaaaaaa");
                UserSp.saveUserBean(userBean);


            }
        });

        findViewById(R.id.tv_chaxun).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                UserBean userBean = UserSp.getUserBean();


                tvContent.setText(userBean.toString() + "");


            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_main;
    }

    public static String getChannel(Context context) {
        String channel = "";
        PackageManager pm = context.getPackageManager();
        try {
            ApplicationInfo ai = pm.getApplicationInfo(
                    context.getPackageName(),
                    PackageManager.GET_META_DATA);
            String value = ai.metaData.getString("CHANNEL");
            if (value != null) {
                channel = value;
            }
        } catch (Exception e) {
// 忽略找不到包信息的异常
        }
        return channel;
    }

}