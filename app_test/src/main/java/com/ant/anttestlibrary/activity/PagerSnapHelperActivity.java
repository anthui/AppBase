package com.ant.anttestlibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.ant.anttestlibrary.R;
import com.ant.anttestlibrary.adapter.PagerSnapHelperAdapter;
import com.ant.app_utils.LogUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;


public class PagerSnapHelperActivity extends Activity {

    /**
     * UI
     */
    // recycleView
    private RecyclerView mRecyclerView = null;
    // adapter
    private PagerSnapHelperAdapter mMyadapter = null;
    /**
     * 数据
     */
    //data
    private ArrayList<String> mDataList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_pager_activity);

        // -----------创建数据集-------------
        for (int i = 1; i < 10; i++) {
            mDataList.add("item" + i);
        }
        // 纵向List
        initUI();

        SmartRefreshLayout viewById = findViewById(R.id.refresh_layout);
        viewById.setOnLoadMoreListener(new OnLoadMoreListener() {
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

    int currentPosition = 0;
    LinearLayoutManager linearLayoutManager;

    public void initUI() {
        // ---RecyclerView---
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setInitialPrefetchItemCount(5);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_vertical);
        mRecyclerView.setNestedScrollingEnabled(false);
        // PagerSnapHelper
        PagerSnapHelper snapHelper = new PagerSnapHelper() {
            // 在 Adapter的 onBindViewHolder 之后执行
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                // TODO 找到对应的Index
                Log.e("xiaxl: ", "---findTargetSnapPosition---");
                int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                Log.e("xiaxl: ", "targetPos: " + targetPos);

                Toast.makeText(PagerSnapHelperActivity.this, "滑到到 " + targetPos + "位置", Toast.LENGTH_SHORT).show();


                View childAt = linearLayoutManager.getChildAt(targetPos);
                //获取当期的viewhole
                RecyclerView.ViewHolder childViewHolder = null;
                if (childAt != null) {
                    childViewHolder = mRecyclerView.getChildViewHolder(childAt);
                }


                View lastViewH = linearLayoutManager.getChildAt(currentPosition);
                //获取当期的viewhole
                RecyclerView.ViewHolder lala = null;
                if (lastViewH != null) {
                    lala = mRecyclerView.getChildViewHolder(lastViewH);
                }


                LogUtil.e("lastPosition=======" + currentPosition + "   viewho==" + (lala == null) + "   ==============     " + targetPos + " + targetPos==========" + (childViewHolder == null));
                currentPosition = targetPos;
                return targetPos;
            }

            // 在 Adapter的 onBindViewHolder 之后执行
            @Nullable
            @Override
            public View findSnapView(RecyclerView.LayoutManager layoutManager) {
                // TODO 找到对应的View
                Log.e("xiaxl: ", "---findSnapView---");
                View view = super.findSnapView(layoutManager);
                Log.e("xiaxl: ", "tag: " + view.getTag());

                return view;
            }
        };
        snapHelper.attachToRecyclerView(mRecyclerView);
        // ---布局管理器---
        // 默认是Vertical (HORIZONTAL则为横向列表)
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mMyadapter = new PagerSnapHelperAdapter(getBaseContext(), mDataList);
        // 设置Adapter
        mRecyclerView.setAdapter(mMyadapter);

        // TODO 这么写是为了获取RecycleView的宽高
//        mRecyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
//                    mRecyclerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
//                } else {
//                    mRecyclerView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//
//                /**
//                 *  这么写是为了获取RecycleView的宽高
//                 */
//                // 创建Adapter，并指定数据集
//                mMyadapter = new PagerSnapHelperAdapter(getBaseContext(), mDataList);
//                // 设置Adapter
//                mRecyclerView.setAdapter(mMyadapter);
//            }
//        });
    }
}
