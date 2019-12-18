package com.ant.anttestlibrary.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.dialog.CommentDialog;
import com.ant.anttestlibrary.dialog.inPutDialog.InputDialog;
import com.ant.app_base.BaseFragment;
import com.ant.app_utils.LogUtil;

import butterknife.BindView;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/18.
 * describe：
 */
public class InputFragment extends BaseFragment {

    @BindView(R.id.et_input)
    EditText etInput;
    @BindView(R.id.tv_send)
    TextView tvSend;
    @BindView(R.id.layout_input)
    LinearLayout layoutInput;
    private InputDialog inputDialog;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_input;
    }

    CommentDialog commentDialog;

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {
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


        rootView.findViewById(R.id.iv_cliclk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentDialog.show(getChildFragmentManager());
            }
        });

        commentDialog = new CommentDialog();

    }
}
