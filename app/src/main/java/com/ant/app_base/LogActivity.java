package com.ant.app_base;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ant.app_home.R;
import com.ant.app_http.Bean.HttpBaseConfig;
import com.ant.app_utils.LogUtil;
import com.bumptech.glide.BuildConfig;
import com.meituan.android.walle.WalleChannelReader;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/4.
 * describe：
 */
public class LogActivity extends BaseActivity {
    private TextView tvContent;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_log;
    }

    @Override
    public void initData() {
        initMessage();

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        tvContent = findViewById(R.id.tv_content);
    }


    private void initMessage() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("日志开关：" + LogUtil.DEBUG);
        stringBuilder.append("\n");
        stringBuilder.append("是否命令打包：" + HttpBaseConfig.host_last);
        stringBuilder.append("\n");
        stringBuilder.append("本地接口域名：" + HttpBaseConfig.URL_HOST);
        stringBuilder.append("\n");
        stringBuilder.append("本地H5接口域名：" + HttpBaseConfig.URL_HOST);
        stringBuilder.append("\n");
        stringBuilder.append("isDebug：" + BuildConfig.DEBUG);
        stringBuilder.append("\n");
        stringBuilder.append("当前渠道：" + WalleChannelReader.getChannel(getApplication()));
        stringBuilder.append("\n");
        stringBuilder.append("version_code：" + BuildConfig.VERSION_CODE);
        stringBuilder.append("\n");
        stringBuilder.append("version_name：" + BuildConfig.VERSION_NAME);
        stringBuilder.append("\n");
        tvContent.setText(stringBuilder.toString());
    }

    public void onclick(View view) {
        if (view.getId() == R.id.tv_log) {
            LogUtil.DEBUG = !LogUtil.DEBUG;
        }
        initData();
    }
}
