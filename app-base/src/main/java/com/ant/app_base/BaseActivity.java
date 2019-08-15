package com.ant.app_base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.ant.app_utils.LogUtil;
import com.ant.app_utils.ToastUtil;

import butterknife.ButterKnife;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2017/11/29
 * describe：所有Acticity类的父类
 * upDate:2019/8/14 除去多余代码（广播等，优化沉浸模式）
 */
public abstract class BaseActivity extends AppCompatActivity implements BaseViewInterFace {


    protected Context mContext;
    protected BaseActivity mActivity;
    protected View mView;

    //分页加载 默认第一页，20
    public int pageIndex = 1;
    public int pageSize = 20;


    //如需要多语言支持 ，打开这里
//    @Override
//    protected void attachBaseContext(Context newBase) {
//        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        优先获取 context
        mContext = getApplicationContext();
        mActivity = this;

        //如果需要去掉 半透明 可以用工具类实现
        // StatusBarUtil.setTransparent(this);
        Window window = getWindow();
        //默认为白色字体
        if (setStateBarTextBlack()) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        if (!hasToolBar()) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (getMainContentViewId() != 0) {
            mView = View.inflate(this, getMainContentViewId(), null);
            setContentView(mView);
        }
        ButterKnife.bind(this);

        //初始化布局
        initComponents(savedInstanceState, mView);
        //初始化列表
        initRecyclerView();
        //初始化数据
        initData();
    }


    /**
     * 这里进行空 实现，不是所有的activity都有列表
     */
    @Override
    public void initRecyclerView() {

    }

    /**
     * 是否需要toolBar
     */
    protected boolean hasToolBar() {
        return true;
    }

    //设置 状态栏黑色字体
    boolean setStateBarTextBlack() {
        return true;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        //   MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //弹窗
    public void showDebugToast(String msg) {
        ToastUtil.showDebugToast(mContext, msg);
    }

    //弹窗
    public void showToast(String msg) {
        ToastUtil.showToast(mContext, msg);
    }


    public void loge(String msg) {
        LogUtil.e(getClass().getSimpleName(), msg);

    }

    //弹窗
    public void showNoOpenToast() {
        showCenterToast(getResources().getString(R.string.no_oppen), false);
        // ToastUtil.showToast(mContext, );
    }


    public void showCenterToast(String msg, boolean success) {
        ToastUtil.showCenterToast(mContext, msg, success);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_current);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_current);
    }

    //重写动画
    public void startActivity(Intent intent, int animIn, int animOut) {
        super.startActivity(intent);
        if (animIn != 0 && animOut != 0) {
            overridePendingTransition(animIn, animOut);
        }
    }

    /**
     * 在finish方法中覆盖原有动画样式，若之前不是通过activity启动的 则不覆盖，使动画样式一致
     */
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.activity_out);
    }

}