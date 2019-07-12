package com.ant.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.utils.AppHelper;
import com.ant.utils.LogUtil;
import com.ant.utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2017/11/25
 * describe：所有Fragmeng的父类
 */
public abstract class BaseFragment extends Fragment {
    protected BaseActivity mActivity;
    protected Context mContext;
    protected LayoutInflater mInflater;
    private View mView = null;
    //  LoadingDialog loadingDialog;
    public final static String FRAGMENT_TYPE = "type";

    //分页加载 默认第一页，加载10条
    protected int pageIndex = 1;
    protected int pageSize = 10;
    //默认页面 类型
    protected int pageType = 0;

    public void initRecycView() {

    }

    protected abstract void initComponents(View view);

    protected abstract void initData();

    protected abstract int getMainContentViewId();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = (BaseActivity) getActivity();
        this.mContext = getActivity().getApplicationContext();
        //  loadingDialog = new LoadingDialog();
    }

//
//    private int getHeight() {
//
//        int statusBarHeight1 = -1;
////获取status_bar_height资源的ID
//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//        if (resourceId > 0) {
//            //根据资源ID获取响应的尺寸值
//            statusBarHeight1 = getResources().getDimensionPixelSize(resourceId);
//        }
//        return statusBarHeight1;
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //  LogUtil.e("onCreateView是否被创建");
        if (mView == null) {
            mView = inflater.inflate(getMainContentViewId(), container, false);
            mInflater = inflater;
            bindViews(mView);

            View view = getChenJinView();
            if (view != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    view.setPadding(0, AppHelper.getStatusBarHeight(mActivity), 0, 0);
                    // LogUtil.e("onCreateView  设置间距================" + getHeight());
                }
            }

            initComponents(mView);
            initRecycView();
            initData();
        } else {
//            ((ViewGroup)mView.getParent()).removeAllViews();
            ViewGroup viewGroup = (ViewGroup) mView.getParent();
            if (viewGroup != null)
                viewGroup.removeView(mView);
        }
        return mView;
    }

    //是否需要 头部沉浸
    public View getChenJinView() {
        return null;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // bindViews(view);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    public void showDebugToast(String msg) {
        ToastUtil.showDebugToast(mContext, msg);
    }

    public void loge(String msg) {
        LogUtil.e(getClass().getSimpleName(), msg);
        // ToastUtil.showdebugToast(getActivity(), msg);
    }

    //弹窗
    public void showToast(String msg) {
        ToastUtil.showToast(mContext, msg);
    }

    //弹窗
    public void showNoOpenToast() {
        ToastUtil.showToast(mContext, getResources().getString(R.string.no_oppen));
    }

    /**
     * 显示进度
     */
    public void showLoaing() {
        //   loadingDialog.show(getChildFragmentManager());
    }

    /**
     * 隐藏进度框
     */
    public void dismissLoading() {
        //    loadingDialog.dismiss();
    }

    public void showDebugCenterToast(String msg) {
        ToastUtil.showDebugCenterToast(mContext, msg);

    }

    //弹窗
    public void showNoDataToast() {
        ToastUtil.showToast(mContext, getResources().getString(R.string.str_net_err_re));
        //    showToast(getString(R.string.str_net_err_re));

    }

    public void showCenterToast(String msg, boolean success) {
        ToastUtil.showCenterToast(mContext, msg, success);
    }

    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.activity_in, R.anim.activity_current);

        //   ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(mActivity, R.anim.slide_in_right, R.anim.slide_out_right);
        // ActivityCompat.startActivity(mActivity, intent, compat.toBundle());
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        mActivity.overridePendingTransition(R.anim.activity_in, R.anim.activity_current);
    }

    //重写动画
    public void startActivity(Intent intent, int animIn, int animOut) {
        super.startActivity(intent);

        if (animIn != 0 && animOut != 0) {
            mActivity.overridePendingTransition(animIn, animOut);
        }
    }
}
