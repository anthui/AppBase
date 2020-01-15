package com.ant.kotlin.activity

import android.os.Bundle
import android.view.View
import com.ant.anttestlibrary.R
import com.ant.app_base.BaseActivity


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/18.
 * describe：
 */


class MyActivity : BaseActivity() {
    override fun getMainContentViewId(): Int {


        return R.layout.activity_dash


    }


    override fun initData() {
    }

    override fun initComponents(savedInstanceState: Bundle, rootView: View) {

    }

}