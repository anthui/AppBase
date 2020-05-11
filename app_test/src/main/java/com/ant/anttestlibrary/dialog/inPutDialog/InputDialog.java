package com.ant.anttestlibrary.dialog.inPutDialog;

import android.content.DialogInterface;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.ant.anttestlibrary.R;
import com.ant.app_base.baseDialog.BaseDialog;
import com.ant.app_utils.CommonUtils;
import com.ant.app_utils.LogUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/17.
 * describe：     键盘弹窗 显示的动画事件 大约250 退出大约300，如果动画事件被修改，要单独设置动画时间
 */
public class InputDialog extends BaseDialog implements ViewTreeObserver.OnGlobalLayoutListener, View.OnClickListener {




    private EditText editText;
    private TextView tvSend;

    private String hintText = "说点什么...";

    private String inputText = "";


    /**
     * 设置字体
     */
    public void setInputText(String inputText) {

        if (inputText == null) {
            return;
        }
        this.inputText = inputText;
    }

    /**
     * 设置预显示文案
     */
    public void setHintText(String hintText) {
        if (TextUtils.isEmpty(hintText)) {
            return;
        }
        this.hintText = hintText;
    }


    @Override
    public int getMainContentViewId() {
        setDimAmount(0.3f);
        setShowBottom(true);
        return R.layout.layout_input;
    }


    /**
     * 注册布局变化监听
     * <p>
     * 这里必须在 initData注册，因为每次关闭弹窗，都会移除监听
     *
     * @param isAdd 是否为注册
     */
    private void setOnGlobalLayoutListener(boolean isAdd) {
        //添加窗体 变化舰艇  窗体要用mActivity，不能用dialog,dialog关闭时，会被干掉
        ViewTreeObserver viewTreeObserver = mActivity.getWindow().getDecorView().getViewTreeObserver();
        if (isAdd) {
            viewTreeObserver.addOnGlobalLayoutListener(this);
        } else {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
        }
    }

    @Override
    public void initData() {
        setOnGlobalLayoutListener(true);
        editText.setHint(hintText);
        editText.setText(inputText);
        tvSend.setOnClickListener(this);

        //要做100毫秒延迟，防止不弹起键盘
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                editText.setText(inputText);
                editText.setSelection(inputText.length());
                CommonUtils.showKeyboard(mContext, editText);
            }
        }, 100);
    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        rootView.findViewById(R.id.tv_input).setVisibility(View.GONE);
        editText = rootView.findViewById(R.id.et_input);
        editText.setVisibility(View.VISIBLE);
        tvSend = rootView.findViewById(R.id.tv_send);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        setOnGlobalLayoutListener(false);
    }

    @Override
    public void onGlobalLayout() {
        Rect rect = new Rect();

        View decorView = mActivity.getWindow().getDecorView();
        decorView.getWindowVisibleDisplayFrame(rect);
        int displayHeight = rect.bottom - rect.top;
        double rate = displayHeight * 1.0 / decorView.getHeight();
        LogUtil.e("屏幕比例================ " + rate);
        if (rate > 0.8) {
            //用dismiss() 可能存在崩溃，息屏时 键盘会被系统关闭，也会关掉键盘
            dismissAllowingStateLoss();
        }

    }

    @Override
    public void onClick(View v) {

        String trim = editText.getText().toString().trim();

        if (onInputMessageCall != null) {
            onInputMessageCall.messageCall(trim);
        }
    }

    /**
     * 设置回调监听
     */
    public void setOnInputMessageCall(InputDialog.onInputMessageCall onInputMessageCall) {
        this.onInputMessageCall = onInputMessageCall;
    }

    private onInputMessageCall onInputMessageCall;

    public interface onInputMessageCall {
        void messageCall(String inputText);
    }
}
