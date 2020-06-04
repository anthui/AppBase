package com.ant.modul.leak;

import android.os.Bundle;
import android.view.View;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.databinding.Aview;
import com.ant.anttestlibrary.dialog.inPutDialog.InputDialog;
import com.ant.app_base.BaseBindActivity;

/**
 * copyright：
 *
 * @author：anthui Version：1.0
 * creation date： 2020/5/11.
 * describe：
 */
public class LeakActivity extends BaseBindActivity<Aview> {
    @Override
    public int getMainContentViewId() {
        return R.layout.activity_lifecycle;
    }

    @Override
    public void initData() {
        Thread thread = new Thread();

    }

    LeakFragment fragment;

    InputDialog inputDialog;
    boolean isCommit = false;

    public static long time = 0;

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        dataBinding.tvContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                time = System.currentTimeMillis();
                if (inputDialog == null) {
                    inputDialog = new InputDialog();
                }
                inputDialog.show(getSupportFragmentManager());


//                inputDialog.dismiss();

//                if (!isCommit) {
//                    fragment = new LeakFragment();
//                    getSupportFragmentManager().beginTransaction()
//                            .add(R.id.frag_a, fragment).commit();
//                } else {
//                    getSupportFragmentManager().beginTransaction()
//                            .remove(fragment).commit();
//                    fragment=null;
//                }
//                isCommit = !isCommit;


            }
        });


    }


}
