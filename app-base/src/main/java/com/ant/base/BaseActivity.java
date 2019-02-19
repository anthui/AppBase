package com.ant.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.ant.utils.LogUtil;
import com.ant.utils.ToastUtil;
import com.ant.utils.language.LocalManageUtil;

import butterknife.ButterKnife;


/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2017/11/29
 * describe：所有Acticity类的父类
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Context mContext;
    public boolean isLoading = false;
    protected BaseActivity mActivity;
    protected View mView;
    private BaseBean bean;
    // public MyHandler mHandler;
    // protected LoadingDialog mProgress;
    private BroadcastReceiver exitBroadcastReceiver;
    //LoadingDialog dialog;

    private String K_EXIST_APP = "exit";
    //分页加载 默认第一页，加载10条
    protected int pageIndex = 1;
    protected int pageSize = 10;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(LocalManageUtil.setLocal(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //4.4后的沉浸模式 最低适配到4.4 这里判断只为以防，理论上不需要判断
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && shouldTop()) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            //大于 6.0时，可修改状态栏颜色
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                if (topBarTextColorWhite()) {

                } else {
                    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

                }
            }


            if (hasToolBar()) {
                //toolbar状态
//                window.setFlags(
//                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                //全屏状态
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);// 设置全屏
            }

        }

        if (getMainContentViewId() != 0) {
            mView = View.inflate(this, getMainContentViewId(), null);
            setContentView(mView); // set view
        }
        //    dialog = new LoadingDialog();
        ButterKnife.bind(this);
        //  mHandler = new MyHandler(this);
        mContext = getApplicationContext(); // get context
        mActivity = this;
        initComponents(savedInstanceState); // init all components
        initRecycView();
        initData(); // init the whole activity's data
        //注册退出广播
        registerExitReceiver();
        //异常捕捉
        registerException();
    }

    /**
     * 是否需要toolBar
     * false为全屏
     */
    protected boolean hasToolBar() {
        return true;
    }


    public void initRecycView() {

    }


    protected boolean topBarTextColorWhite() {
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

    /**
     * 是否需要
     */
    public boolean shouldTop() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registerExitReceiver();
    }


    /**
     * 注册全局异常处理类
     */
    private void registerException() {
        UxExceptionHelper.getInstance().register(mContext);
    }

    public void handleMessage(Message msg) {
    }

    /**
     * 初始化UI组件及数据
     */
    protected abstract void initComponents(Bundle savedInstanceState);

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 布局ID
     */
    protected abstract int getMainContentViewId();


    /**
     * 注册退出应用广播,默认注册 发送动作为action_exit_app的广播，可销毁所有Activity <br/>
     * 不想当前activity被销毁，可覆盖该方法
     */
    protected void registerExitReceiver() {
        exitBroadcastReceiver = new ExitBroadcastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(K_EXIST_APP);
        LocalBroadcastManager.getInstance(mContext).registerReceiver(
                exitBroadcastReceiver, filter);
    }

    /**
     * 注销广播 当前activity销毁，自动调用该方法
     */
    protected void unRegisterExitReceiver() {
        if (exitBroadcastReceiver != null) {
            LocalBroadcastManager.getInstance(mContext).unregisterReceiver(
                    exitBroadcastReceiver);
            exitBroadcastReceiver = null;
        }
    }


    /**
     * 退出应用程序 发送动作为action_exit_app的广播，销毁所有注册了广播的activity
     */
    public void exitApp() {
        sendBroadcast(new Intent(K_EXIST_APP));
    }

    // 接收退出广播
    private class ExitBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context arg0, Intent arg1) {
            finish();
        }
    }

    //弹窗
    public void showdebugToast(String msg) {
        ToastUtil.showDebugToast(mContext, msg);
    }

    //弹窗
    public void showToast(String msg) {
        ToastUtil.showToast(mContext, msg);

        //     showCenterToast(msg, false);

    }


    public void loge(String msg) {
        LogUtil.e(getClass().getSimpleName(), msg);

    }

    //弹窗
    public void showNoOpenToast() {
        showCenterToast(getResources().getString(R.string.no_oppen), false);
        // ToastUtil.showToast(mContext, );
    }

    //弹窗
    public void showNoDataToast() {
        //   showCenterToast(getResources().getString(R.string.str_net_err_re), false);

        showToast(getString(R.string.str_net_err_re));

    }

    /**
     * 显示进度
     */
    public void showLoaing() {
        //     dialog.show(getSupportFragmentManager());
    }

    /**
     * 隐藏进度框
     */
    public void dismissLoading() {

        //    dialog.dismiss();

    }

    public void showDebugCenterToast(String msg) {
        ToastUtil.showDebugCenterToast(mContext, msg);

    }


    public void showCenterToast(String msg, boolean success) {
        ToastUtil.showCenterToast(mContext, msg, success);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_current);

        //   ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(mActivity, R.anim.slide_in_right, R.anim.slide_out_right);
        // ActivityCompat.startActivity(mActivity, intent, compat.toBundle());
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

    public void finish(boolean hasAnimation) {
        //super.finish();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            finishAfterTransition();
        } else {
            // mActivity.startActivity(i);
            finish();
            // overridePendingTransition(0, R.anim.activity_out);
        }

    }

}