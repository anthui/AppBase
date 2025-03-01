package com.ant.app_base.baseDialog;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ant.app_base.BaseViewInterFace;
import com.ant.app_base.R;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.ToastUtil;

import butterknife.ButterKnife;


/**
 * copyright：""
 * author：anthui
 * Version：1.0
 * creation date：2018/2/2
 * describe： 基础弹框
 */
public abstract class BaseDialog extends DialogFragment implements BaseViewInterFace {
    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String BOTTOM = "show_bottom";
    private static final String CANCEL = "out_cancel";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";

    private int margin;//左右边距
    private int width;//宽度
    private int height;//高度
    private float dimAmount = 0.5f;//灰度深浅
    private boolean showBottom;//是否底部显示
    private boolean outCancel = true;//是否点击外部取消
    @StyleRes
    private int animStyle;
    @LayoutRes
    protected int layoutId;

    public Context mContext;
    public AppCompatActivity mActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.NiceDialog);
        layoutId = getMainContentViewId();
        mContext = getContext();
        mActivity = (AppCompatActivity) getActivity();
        //恢复保存的数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            showBottom = savedInstanceState.getBoolean(BOTTOM);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }
    }

    private void bindViews(View view) {
        ButterKnife.bind(this, view);
    }

    protected View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(layoutId, container, false);
            bindViews(mView);
            initComponents(null, mView);
            initRecyclerView();
        } else {
            ViewGroup viewGroup = (ViewGroup) mView.getParent();
            if (viewGroup != null)
                viewGroup.removeView(mView);
        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();

    }


    @Override
    public void initRecyclerView() {

    }

    @Override
    public void onStart() {
        super.onStart();
        initParams();
    }

    public void showCenterToast(String msg, boolean success) {
        ToastUtil.showCenterToast(mContext, msg, success);
    }

    /**
     * 屏幕旋转等导致DialogFragment销毁后重建时保存数据
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MARGIN, margin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putBoolean(BOTTOM, showBottom);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, layoutId);
    }

    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            //调节灰色背景透明度[0-1]，默认0.5f
            lp.dimAmount = dimAmount;
            //是否在底部显示
            if (showBottom) {
                lp.gravity = Gravity.BOTTOM;
                if (animStyle == 0) {
                    animStyle = R.style.DefaultAnimation;
                }
            }

            //设置dialog宽度
            if (width == 0) {
                lp.width = getScreenWidth(getContext()) - 2 * dp2px(getContext(), margin);
            } else if (width == -1) {
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                lp.width = dp2px(getContext(), width);
            }

            //设置dialog高度
            if (height == -1) {
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
            } else if (height > 0) {

                lp.height = dp2px(getContext(), height);
            } else {
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

            }

            //设置dialog进入、退出的动画
            window.setWindowAnimations(animStyle);
            window.setAttributes(lp);
        }
        setCancelable(outCancel);
    }

    public BaseDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    public BaseDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    public BaseDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    public BaseDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    public BaseDialog setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    public BaseDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }

    public BaseDialog setAnimStyle(@StyleRes int animStyle) {
        this.animStyle = animStyle;
        return this;
    }


    public int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public BaseDialog show(FragmentManager manager) {
        //已经添加

        Fragment fragmentByTag = manager.findFragmentByTag(getClass().getSimpleName());
        LogUtil.e("msg========    " + fragmentByTag);

        if (getDialog() != null && getDialog().isShowing()) {
            LogUtil.e("msg========    lanjie");

            return this;
        }


        LogUtil.e("==================== " + isAdded());
        if (isAdded()) {
            return this;
        }

        super.show(manager, getClass().getSimpleName());
        return this;
    }

    /**
     * 隐藏
     */
    public void dismiss() {
        if (getDialog() != null && getDialog().isShowing()) {
            super.dismiss();
        }
    }

    //弹窗
    public void showdebugToast(String msg) {
        ToastUtil.showDebugToast(getContext(), msg);
    }

    @Override
    public void showDebugToast(String msg) {

    }

    //弹窗
    public void showToast(String msg) {
        ToastUtil.showToast(getContext(), msg);
    }


    public void loge(String msg) {
        LogUtil.e(getClass().getSimpleName(), msg);

    }

    @Override
    public void showNoOpenToast() {

    }

}
