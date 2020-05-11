package com.ant.app_base.baseDialog;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.ant.app_utils.LogUtil;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/4/28.
 * describe： MyDialog
 */
public class MyDialog extends Dialog {
    public MyDialog(@NonNull Context context) {
        super(context);
    }

    public MyDialog(Context requireContext, int theme) {
        super(requireContext, theme);
    }


    @Override
    public void dismiss() {
        LogUtil.e("msg==================================dismiss");
        super.cancel();
    }

}
