package com.ant.anttestlibrary.dialog;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.dialog.inPutDialog.InputDialog;
import com.ant.app_base.baseDialog.BaseDialog;
import com.ant.app_utils.AppHelper;
import com.ant.app_utils.LogUtil;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/18.
 * describe：
 */
public class CommentDialog extends BaseDialog {

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.layout_input)
    LinearLayout layoutInput;
    private InputDialog inputDialog;

    @Override
    public int getMainContentViewId() {
        setShowBottom(true);
        setDimAmount(0.4f);

//        View decorView = mActivity.getWindow().getDecorView();
//        int screenHeight = AppHelper.getScreenHeight(mContext);
//        setHeight(screenHeight/)

        return R.layout.dialog_input;
    }

    @Override
    public void initData() {

    }

    CommentDialog commentDialog;

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {
//如果外部 弹窗会变动，可以设置这个
        //        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
        inputDialog = new InputDialog();
        inputDialog.setOnInputMessageCall(new InputDialog.onInputMessageCall() {
            @Override
            public void messageCall(String inputText) {
                LogUtil.e("输入内容：  " + inputText);
            }
        });
        layoutInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogUtil.e("hahahha===============================");
                inputDialog.setHintText("哈哈哈");
//                inputDialog.setInputText("ooooo");
                inputDialog.show(getChildFragmentManager());
            }
        });
        rootView.findViewById(R.id.view_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        rootView.findViewById(R.id.tv_title).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LogUtil.e("========================================");
                if (commentDialog == null) {
                    commentDialog = new CommentDialog();
                }
                commentDialog.show(getChildFragmentManager());
            }
        });
    }
}
