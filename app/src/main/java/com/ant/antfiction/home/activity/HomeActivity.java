package com.ant.antfiction.home.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ant.antfiction.R;
import com.ant.antfiction.home.fragment.HomeFragment1;
import com.ant.antfiction.three.getui.DemoIntentService;
import com.ant.antfiction.three.getui.DemoPushService;
import com.ant.antfiction.utils.version.NewVersionHelper;
import com.ant.base.BaseActivity;
import com.ant.utils.ActivityStackManager;
import com.ant.utils.PermissionsHelper;
import com.ant.views.EmptyView;
import com.ant.views.bottomtabbar.BottomTabBar;
import com.igexin.sdk.PushManager;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/5/14
 * describe： 首页
 */
@SuppressLint("NewApi")
public class HomeActivity extends BaseActivity {
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottomTabBar;
    @BindView(R.id.empty_view)
    EmptyView emptyView;
    private PermissionsHelper permissionsHelper;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionsHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    //                bottomTabBar.init(getSupportFragmentManager())
////                .addTabItem("商城", R.mipmap.icon_shopping2, R.mipmap.icon_shopping1, BrowserFragment2.class, false)
////                        .addTabItem(getString(R.string.str_home), R.mipmap.icon_home1, R.mipmap.icon_home2, HomeFragment.class, false)
//
//                        .addTabItem(getString(R.string.str_mining), R.mipmap.icon_mining, R.mipmap.icon_mining2, MiningFragment.class, false)
//                        //   .addTabItem(getString(R.string.str_flash), R.mipmap.icon_dui2, R.mipmap.icon_dui1, FlashFragment.class, false)
//                        //   .addTabItem("合约", R.mipmap.icon_jiaoyi2, R.mipmap.icon_jiaoyi1, BrowserFragment.class, false)
//                        .addTabItem(getString(R.string.str_setting), R.mipmap.icon_setting1, R.mipmap.icon_setting2, SettingFragment.class, false)
//                        .isShowDivider(false);
    @Override
    protected void initComponents(Bundle savedInstanceState) {
        permissionsHelper = new PermissionsHelper(mActivity);

//        BottomTabBar init = bottomTabBar.init(getSupportFragmentManager());
//        init.addTabItem(getString(R.string.str_home), R.mipmap.icon_home1, R.mipmap.icon_home2, MiningFragment.class, false);
//        init.addTabItem(mContext.getString(R.string.str_appli), R.mipmap.icon_mining, R.mipmap.icon_mining2, ApplicationFragment.class, false);
//        init.addTabItem(mContext.getString(R.string.str_mining), R.mipmap.icon_shop1, R.mipmap.icon_shop2, BrowserFragment.class, false);
//
//        //添加中间 部分
//        //   AppNameHelper.getAppNameHelper().initHomeBottomBar(init, mContext);
//        init.addTabItem(getString(R.string.str_mine), R.mipmap.icon_setting1, R.mipmap.icon_setting2, MineFragment.class, false);
//

        bottomTabBar.init(getSupportFragmentManager())

                .addTabItem("首页", R.mipmap.icon_home1, R.mipmap.icon_home2, HomeFragment1.class, false)
                .addTabItem("haha1", R.mipmap.icon_home1, R.mipmap.icon_home2, HomeFragment1.class, false)
                .isShowDivider(false);


        //个推推送
        PushManager.getInstance().initialize(this, DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(this, DemoIntentService.class);

        //    loge("启动 个推服务");
//        CarNumListener.getCarListener().addOnCarNumChangeListener(new CarNumListener.OnCarNumChangeListener() {
//            @Override
//            public void onCarNumChange(int num) {
//                //    bottomTabBar.setBottomBarNum(1, num);
//                //   bottomTabBar.setBottomBarNum(0, num);
//                //   bottomTabBar.setBottomBarNum(2, num);
//            }
//        });

        //首页后 清空之前的 所有的页面  主要为登录那边使用
        ActivityStackManager.getInstance().killAllActivity();

        //   CarNumListener.getCarListener().setCarNum(3);

    }


    NewVersionHelper newVersionHelper;

    public void checkNewVersion(final boolean toast) {


        if (newVersionHelper == null) {
            newVersionHelper = new NewVersionHelper(mContext, emptyView, permissionsHelper);
        }

        newVersionHelper.checkNewVersion(getSupportFragmentManager(), toast);

//
    }

    @Override
    protected void initData() {

        // checkNewVersion(false);

    }

    //对返回键进行监听
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

//        if (currentPosition == 2) {
//
//            BrowserFragment browserFragment = getBrowserFragment();
//            if (browserFragment != null) {
//                boolean b = browserFragment.onKeyDown(keyCode, event);
//                if (b) {
//                    return true;
//                }
//            }
//        }


        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected int getMainContentViewId() {
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
            System.exit(0);
        }
    }

    private int currentPosition = 0;


}
