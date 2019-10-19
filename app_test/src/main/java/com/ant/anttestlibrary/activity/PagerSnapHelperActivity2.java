package com.ant.anttestlibrary.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.adapter.PagerSnapHelperAdapter;
import com.ant.app_base.BaseActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;

import butterknife.BindView;


public class PagerSnapHelperActivity2 extends BaseActivity {


    @BindView(R.id.view_page)
    ViewPager2 viewPage;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private PagerSnapHelperAdapter mMyadapter;
    private ArrayList<String> mDataList = new ArrayList<String>();

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_viewpage2;
    }

    @Override
    public void initData() {

    }


    @Override
    public void initRecyclerView() {
        super.initRecyclerView();
        for (int i = 1; i < 10; i++) {
            mDataList.add("item" + i);
        }
        mMyadapter = new PagerSnapHelperAdapter(getBaseContext(), mDataList);
        viewPage.setOrientation(ViewPager2.ORIENTATION_VERTICAL);
        viewPage.setAdapter(mMyadapter);

        refreshLayout.setSmallVideoConfig();
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishLoadMore()
                        ;
                        for (int i = 1; i < 10; i++) {
                            mDataList.add("item" + i);
                        }
                        mMyadapter.notifyDataSetChanged();

                    }
                }, 2000);
            }
        });
    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

    }


}
