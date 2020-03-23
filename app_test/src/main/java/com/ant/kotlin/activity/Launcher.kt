package com.ant.kotlin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.ant.anttestlibrary.R
import com.ant.anttestlibrary.databinding.ActivityHom2Binding
import com.ant.app_base.BaseBindActivity


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/12/18.
 * describe：
 */

class Launcher : BaseBindActivity<ActivityHom2Binding>() {


    override fun initComponents(savedInstanceState: Bundle?, rootView: View?) {

        var tvTitle = findViewById<TextView>(R.id.tv_title)
        tvTitle.setText("Kotlin")

        dataBinding.tvRefresh.setOnClickListener { view ->

            startActivity(Intent(mActivity, LifeTestActivity::class.java))


        }


    }


    override fun initData() {
    }

    override fun getMainContentViewId(): Int {
        return R.layout.activity_hom2
    }

}