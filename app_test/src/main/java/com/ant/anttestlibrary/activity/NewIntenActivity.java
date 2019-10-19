package com.ant.anttestlibrary.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseActivity;
import com.ant.app_utils.LogUtil;
import com.ant.app_views.bottomtabbar.BottomTabBar;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/26.
 * describe：
 */
public class NewIntenActivity extends BaseActivity {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.view_line_bar)
    View viewLineBar;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_home;
    }

    @Override
    public void initData() {

        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(mActivity, LauncherActivity.class));
            }
        });
    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LogUtil.e("============================onCreate============================" + hashCode());

    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.e("============================onStart============================" + hashCode());

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.e("============================onRestart============================" + hashCode());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        LogUtil.e("============================onNewIntent============================" + hashCode());
    }

    @OnClick({R.id.iv_back, R.id.tv_title, R.id.tv_right, R.id.iv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                Intent intent = new Intent(mActivity, NewIntenActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

                break;
            case R.id.tv_title:
                break;
            case R.id.tv_right:
                break;
            case R.id.iv_right:
                break;
        }
    }
}
