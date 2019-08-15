package com.ant.app_base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ant.app_utils.AppHelper;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.ToastUtil;

import butterknife.ButterKnife;

/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2017/11/25
 * describe：所有Fragmeng的父类
 */
public abstract class BaseFragment extends Fragment implements BaseViewInterFace {
    protected BaseActivity mActivity;
    protected Context mContext;
    protected LayoutInflater mInflater;
    private View mView = null;

    //分页加载 默认第一页，加载10条
    protected int pageIndex = 1;
    //分页加载默认数量
    protected int pageSize = 20;
    //默认页面 类型
    protected int pageType = 0;

    public void initRecyclerView() {


    }

    //是否打印 生命周期
    final boolean showLiveLog = false;

    private void showLiveLog(String tag) {

        if (showLiveLog) {
            LogUtil.e("fragment_live", tag);
        }
    }


    //    ******************************fragment生命周期*******************************
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mActivity = (BaseActivity) getActivity();
        this.mContext = getActivity().getApplicationContext();
        showLiveLog("onCreate=================== ");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        showLiveLog("onCreateView");
        View rootView = getRootView(inflater, container);
        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        showLiveLog("onDetach");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        showLiveLog("onAttach");

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLiveLog("onViewCreated");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        showLiveLog("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showLiveLog("onDestroy");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showLiveLog("onActivityCreated");

    }

    //    ******************************fragment生命周期*******************************


    //是否需要 头部沉浸
    public View getChenJinView() {
        return null;
    }


    private void bindViews(View view) {
        ButterKnife.bind(this, view);
    }


    //    ******************************基础日志弹窗*******************************


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

    //    ******************************切面切换 动画*******************************

    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(R.anim.activity_in, R.anim.activity_current);
    }

    @Override
    public void startActivity(Intent intent) {
        // super.startActivity(intent);
        //  mActivity.overridePendingTransition(R.anim.activity_in, R.anim.activity_current);

        startActivity(intent, R.anim.activity_in, R.anim.activity_current);
    }

    //重写动画
    public void startActivity(Intent intent, int animIn, int animOut) {
        super.startActivity(intent);

        if (animIn != 0 && animOut != 0) {
            mActivity.overridePendingTransition(animIn, animOut);
        }
    }


    /**
     * 获取 UI页面
     * <p>
     * 子页面 通过 butterKnife实现
     */

    private View getRootView(LayoutInflater inflater, ViewGroup container) {
        if (mView == null) {
            mView = inflater.inflate(getMainContentViewId(), container, false);
            mInflater = inflater;
            bindViews(mView);

            View view = getChenJinView();
            if (view != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    view.setPadding(0, AppHelper.getStatusBarHeight(mActivity), 0, 0);
                }
            }
            initComponents(null, mView);
            initRecyclerView();
            initData();
        } else {
            ViewGroup viewGroup = (ViewGroup) mView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(mView);
            }
        }

        return mView;
    }

}
