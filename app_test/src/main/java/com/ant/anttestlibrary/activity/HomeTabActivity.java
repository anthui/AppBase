package com.ant.anttestlibrary.activity;


import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.fragment.DataBaseFragment;
import com.ant.anttestlibrary.fragment.HomeRefreshFragment;
import com.ant.anttestlibrary.fragment.HomeTabFragment;
import com.ant.anttestlibrary.fragment.TabLayoutFragment;
import com.ant.app_base.BaseActivity;
import com.ant.app_utils.PermissionsHelper;
import com.ant.app_views.bottomtabbar.BottomTabBar;


public class HomeTabActivity extends BaseActivity {
    BottomTabBar bottomTabBar;
    //    @BindView(R.id.empty_view)
//    EmptyView emptyView;
    private PermissionsHelper permissionsHelper;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    public void initComponents(Bundle savedInstanceState, View view) {
        permissionsHelper = new PermissionsHelper(mActivity);

        bottomTabBar = findViewById(R.id.bottom_tab_bar);

        bottomTabBar.init(getSupportFragmentManager())

                .addTabItem("刷新", R.mipmap.icon_home1, R.mipmap.icon_home2, HomeRefreshFragment.class)
                .addTabItem("数据库", R.mipmap.icon_home1, R.mipmap.icon_home2, DataBaseFragment.class)
                .addTabItem("Tab", R.mipmap.icon_home1, R.mipmap.icon_home2, TabLayoutFragment.class)
                .addTabItem("ww", R.mipmap.icon_home1, R.mipmap.icon_home2, TabLayoutFragment.class)
                .addTabItem("haha1", R.mipmap.icon_home1, R.mipmap.icon_home2, HomeTabFragment.class)

//                .addTabItem("哈哈", R.mipmap.icon_home1, R.mipmap.icon_home2, HomeTabFragment.class)
//                .addTabItem("我的", R.mipmap.icon_home1, R.mipmap.icon_home2, HomeTabFragment.class)
                .isShowDivider(false);

        bottomTabBar.setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
            @Override
            public void onTabChange(int position, String name, View view) {
                bottomTabBar.setBottomBarNum(position, 12);
            }
        });

    }

    @Override
    public void initData() {

        // checkNewVersion(false);
    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {


        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    public int getMainContentViewId() {
        return R.layout.activity_home;
    }

    //
    long mExitTime;

    public void exit() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            Toast.makeText(mContext, R.string.str_exit, Toast.LENGTH_SHORT).show();
            mExitTime = System.currentTimeMillis();
        } else {
            finish();

            //启动线程 操作关闭
//            System.exit(0);
        }
    }


}
