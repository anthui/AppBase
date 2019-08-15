package com.ant.anttestlibrary.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.SearchView;
import com.ant.app_base.BaseFragment;
import com.ant.app_base.baseAdapter.BaseAdapter;
import com.ant.app_base.baseAdapter.base.ViewHolder;
import com.ant.app_utils.LogUtil;
import com.ant.app_utils.date.RxTimer;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2019/8/6.
 * describe：
 */
public class HomeRefreshFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    private ArrayList<String> strings;
    private SearchView searchView;


    @Override
    public void initComponents(Bundle savedInstanceState,View view) {

        searchView = view.findViewById(R.id.search_view);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchView.initAll();
            }
        });

        recyclerView = view.findViewById(R.id.recycler_view);

        refreshLayout = view.findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                pageIndex++;
                getData();

            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                pageIndex = 1;
                getData();
            }
        });
    }

    @Override
    public void initRecyclerView() {
        super.initRecyclerView();

        strings = new ArrayList<>();


        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        baseAdapter = new BaseAdapter<String>(mContext, R.layout.item_list, strings) {
            @Override
            protected void convert(ViewHolder holder, String s, int position) {

//                holder.setText(R.id.tv_name, );
            }


        };

        recyclerView.setAdapter(baseAdapter);

    }


    BaseAdapter baseAdapter;

    private void getData() {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 2000);
        new RxTimer().timer(2000, new RxTimer.RxAction() {
            @Override
            public void action(long number) {

                refreshLayout.loadFinish(pageIndex, 3);
                if (pageIndex == 1) {
                    strings.clear();
                }

                strings.add("");
                strings.add("");
                strings.add("");
                strings.add("");
                strings.add("");
                strings.add("");
                strings.add("");
                baseAdapter.notifyDataSetChanged();
                LogUtil.w("==================");
            }
        });

    }

    @Override
    public void initData() {
        getData();

    }

    @Override
    public int getMainContentViewId() {
        return R.layout.fragment_home_refresh;
    }
}
