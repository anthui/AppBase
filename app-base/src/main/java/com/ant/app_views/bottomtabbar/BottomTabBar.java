package com.ant.app_views.bottomtabbar;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTabHost;

import com.ant.app_base.R;
import com.ant.app_views.bottomtabbar.custom.CustomFragmentTabHost;

import java.util.ArrayList;
import java.util.List;

/**
 * copyright：
 * author：
 * Version：1.0
 * creation date： 更新2019/8/6
 * describe：
 * <p>
 * 默认使用fragmentTabHost，如果需要保持fragment 只需要 替换为 CustomFragmentTabHost 就可
 */
public class BottomTabBar extends LinearLayout {

    private Context context;
    private View mLayout;
    //BottomTabBar整体布局，这里使用FragmentTabHost
    private CustomFragmentTabHost mTabHost;
    //分割线
    private View mDivider;

    //图片的宽高
    private float imgWidth = 0, imgHeight = 0;
    //文字尺寸
    private float fontSize = 14;
    //文字图片的间隔
    private float fontImgPadding;
    //上边距和下边距
//    private float paddingTop, paddingBottom;
    //tabHost高度
    private float tab_height = 0;
    //选中未选中的颜色
    private int selectColor = 0, unSelectColor = 0;
    //分割线高度
    private float dividerHeight;
    //是否显示分割线
    private boolean isShowDivider = false;
    //分割线背景
    private int dividerBackgroundColor = 0;
    //BottomTabBar的整体背景
    private int tabBarBackgroundColor = 0;
    //tabId集合
    private List<String> tabIdList = new ArrayList<>();


    public void setBottomVisiable(int visiable) {
        mTabHost.setVisibility(visiable);
    }

    public int bottomIsVisiable() {

        return mTabHost.getVisibility();
    }


    List<TextView> textViews = new ArrayList<>();


    /**
     * 设置小红点
     */
    public void setBottomBarNum(int position, int num) {


        if (textViews.size() < position) {
            return;
        }

        TextView textView = textViews.get(position);
        textView.setText(num + "");
        textView.setVisibility(num == 0 ? View.GONE : View.VISIBLE);
    }

    /**
     * Tab标签切换监听
     */
    public interface OnTabChangeListener {
        void onTabChange(int position, String name, View view);

    }

    private OnTabChangeListener listener;

    public BottomTabBar(Context context) {
        super(context);
        this.context = context;
    }

    public BottomTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.BottomTabBar);
        if (attributes != null) {
            //图片宽度
            imgWidth = attributes.getDimension(R.styleable.BottomTabBar_tab_img_width, 0);
            //图片高度
            imgHeight = attributes.getDimension(R.styleable.BottomTabBar_tab_img_height, 0);
            //文字尺寸
            fontSize = attributes.getDimension(R.styleable.BottomTabBar_tab_font_size, 14);
            //tabHost高度
            tab_height = attributes.getDimension(R.styleable.BottomTabBar_tab_height, dp2px(50));
            //图片文字间隔
            fontImgPadding = attributes.getDimension(R.styleable.BottomTabBar_tab_img_font_padding, dp2px(3));
            //下边距
//            paddingBottom = attributes.getDimension(R.styleable.BottomTabBar_tab_padding_bottom, dp2px(5));
            //分割线高度
            dividerHeight = attributes.getDimension(R.styleable.BottomTabBar_tab_divider_height, dp2px(1));
            //是否显示分割线
            isShowDivider = attributes.getBoolean(R.styleable.BottomTabBar_tab_isshow_divider, false);
            //选中的颜色
            selectColor = attributes.getColor(R.styleable.BottomTabBar_tab_selected_color, Color.parseColor("#333333"));
            //未选中的颜色
            unSelectColor = attributes.getColor(R.styleable.BottomTabBar_tab_unselected_color, Color.parseColor("#666666"));
            //BottomTabBar的整体背景
            tabBarBackgroundColor = attributes.getColor(R.styleable.BottomTabBar_tab_bar_background, Color.parseColor("#ffffff"));
            //分割线背景
            dividerBackgroundColor = attributes.getColor(R.styleable.BottomTabBar_tab_divider_background, Color.parseColor("#CCCCCC"));

            attributes.recycle();
        }
    }

    /**
     * 初始化，主要是需要传入一个FragmentManager
     * <p>
     * 此方法必须在所有的方法之前调用
     *
     * @param manager
     * @return
     */
    public BottomTabBar init(FragmentManager manager) {
        mLayout = LayoutInflater.from(context).inflate(R.layout.bottom_tab_bar, null);
//        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        //这里必须添加 layoutParams
        mLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(mLayout);
        mTabHost = mLayout.findViewById(android.R.id.tabhost);

        ViewGroup.LayoutParams layoutParams = mTabHost.getLayoutParams();
        layoutParams.height = (int) tab_height;
        mTabHost.setLayoutParams(layoutParams);
        mTabHost.setup(context, manager, R.id.home_content);
        mTabHost.setBackgroundColor(tabBarBackgroundColor);
        mTabHost.getTabWidget().setDividerDrawable(null);
        tabIdList.clear();


        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (listener != null) {
                    listener.onTabChange(mTabHost.getCurrentTab(), tabId, mTabHost.getCurrentTabView());
                }
            }
        });

        mDivider = mLayout.findViewById(R.id.home_split);
        if (isShowDivider) {
            LayoutParams dividerParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) dividerHeight);
            mDivider.setLayoutParams(dividerParams);
            mDivider.setBackgroundColor(dividerBackgroundColor);
            mDivider.setVisibility(VISIBLE);
        } else {
            mDivider.setVisibility(GONE);
        }

        return this;
    }

    public BottomTabBar setOnTabChangeListener(OnTabChangeListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
        return this;
    }


    /**
     * 添加底部tab
     *
     * @param name          底部名称
     * @param fragmentClass FragmentClass
     * @param imgIdSelect   图片选中的resID
     * @param imgIdUnSelect 图片未选中的resID
     */
    public BottomTabBar addTabItem(String name, int imgIdSelect, int imgIdUnSelect, Class fragmentClass) {
        return addTabItem(name, ContextCompat.getDrawable(context, imgIdSelect), ContextCompat.getDrawable(context, imgIdUnSelect), fragmentClass);
    }


    public BottomTabBar addTabItem(final String name, Drawable drawableSelect, Drawable drawableUnSelect, Class fragmentClass) {
        tabIdList.add(TextUtils.isEmpty(name) ? fragmentClass.getName() : name);
        mTabHost.addTab(mTabHost.newTabSpec(TextUtils.isEmpty(name) ? fragmentClass.getName() : name)
                .setIndicator(getTabItemView(TextUtils.isEmpty(name) ? fragmentClass.getName() : name, drawableSelect, drawableUnSelect)), fragmentClass, null);
        return this;
    }


    /**
     * 添加tab
     */
    private View getTabItemView(String name, Drawable drawableSelect, Drawable drawableUnSelect) {
        View view = LayoutInflater.from(context).inflate(R.layout.tab_item, null);

        //外部使用 LinearLayout 并使用 权重 weight  排列 tab
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) tab_height);
        layoutParams.weight = 1;
        view.setLayoutParams(layoutParams);

        //保存 消息 小红点 textView
        textViews.add((TextView) view.findViewById(R.id.tv_message));

        //设置图片宽高、位置
        ImageView iv = (ImageView) view.findViewById(R.id.tab_item_iv);

        RelativeLayout.LayoutParams ivParams = new RelativeLayout.LayoutParams(imgWidth == 0 ? LayoutParams.WRAP_CONTENT : (int) imgWidth, imgHeight == 0 ? LayoutParams.WRAP_CONTENT : (int) imgHeight);
        ivParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        iv.setLayoutParams(ivParams);

        //图片选中状态 设置
        StateListDrawable drawable = new StateListDrawable();
        drawable.addState(new int[]{android.R.attr.state_pressed},
                drawableSelect);
        drawable.addState(new int[]{android.R.attr.state_selected},
                drawableSelect);
        drawable.addState(new int[]{},
                drawableUnSelect);
        iv.setImageDrawable(drawable);

        //文字颜色 状态设置
        TextView tv = (TextView) view.findViewById(R.id.tab_item_tv);
        tv.setText(name);
        tv.setPadding(0, (int) fontImgPadding, 0, 0);
        tv.setTextSize(fontSize);
        int[][] states = new int[3][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{android.R.attr.state_selected};
        states[2] = new int[]{};
        int[] colors = new int[]{selectColor, selectColor, unSelectColor};//colorSelect跟states[0]对应，color跟states[2]对应，以此类推
        ColorStateList csl = new ColorStateList(states, colors);
        tv.setTextColor(csl);

        return view;
    }

    //=========================参数设置START========================================

    /**
     * 设置图片的尺寸
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param width  宽度 px
     * @param height 高度 px
     * @return
     */
    public BottomTabBar setImgSize(float width, float height) {
        this.imgWidth = width;
        this.imgHeight = height;
        return this;
    }

    /**
     * 设置文字的尺寸
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param textSize 文字的尺寸 sp
     * @return
     */
    public BottomTabBar setFontSize(float textSize) {
        this.fontSize = textSize;
        return this;
    }

    /**
     * 设置Tab的padding值
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param top    上边距 px
     * @param middle 文字图片的距离 px
     * @param bottom 下边距 px
     * @return
     */
    public BottomTabBar setTabPadding(float top, float middle, float bottom) {
//        this.paddingTop = top;
        this.fontImgPadding = middle;
//        this.paddingBottom = bottom;
        return this;
    }

    /**
     * 设置选中未选中的颜色
     * <p>
     * 此方法必须在addTabItem()之前调用
     *
     * @param selectColor   选中的颜色
     * @param unSelectColor 未选中的颜色
     * @return
     */
    public BottomTabBar setChangeColor(@ColorInt int selectColor, @ColorInt int unSelectColor) {
        this.selectColor = selectColor;
        this.unSelectColor = unSelectColor;
        return this;
    }

    /**
     * 设置BottomTabBar的整体背景
     *
     * @param color 背景颜色
     * @return
     */
    public BottomTabBar setTabBarBackgroundColor(@ColorInt int color) {
        this.tabBarBackgroundColor = color;
        mTabHost.setBackgroundColor(color);
        return this;
    }

    /**
     * 设置BottomTabBar的整体背景
     *
     * @param resid 背景图片id
     * @return
     */
    public BottomTabBar setTabBarBackgroundResource(@DrawableRes int resid) {
        mTabHost.setBackgroundResource(resid);
        return this;
    }
//    /**
//     * 设置BottomTabBar的整体背景
//     * api 16开始才支持
//     *
//     * @param drawable 背景图片
//     * @return
//     */
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    public BottomTabBar setTabBarBackgroundResource(Drawable drawable){
//        mTabHost.setBackground(drawable);
//        return this;
//    }

    /**
     * 是否显示分割线
     *
     * @param isShowDivider
     * @return
     */
    public BottomTabBar isShowDivider(boolean isShowDivider) {
        this.isShowDivider = isShowDivider;
        if (isShowDivider) {
            LayoutParams dividerParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) dividerHeight);
            mDivider.setLayoutParams(dividerParams);
            mDivider.setBackgroundColor(dividerBackgroundColor);
            mDivider.setVisibility(VISIBLE);
        } else {
            mDivider.setVisibility(GONE);
        }
        return this;
    }

    /**
     * 设置分割线的高度
     *
     * @param height
     * @return
     */
    public BottomTabBar setDividerHeight(float height) {
        this.dividerHeight = height;
        if (isShowDivider) {
            LayoutParams dividerParams = new LayoutParams(LayoutParams.MATCH_PARENT, (int) dividerHeight);
            mDivider.setLayoutParams(dividerParams);
        }
        return this;
    }

    /**
     * 设置分割线的颜色
     *
     * @param color
     * @return
     */
    public BottomTabBar setDividerColor(@ColorInt int color) {
        this.dividerBackgroundColor = color;
        if (isShowDivider) {
            mDivider.setBackgroundColor(dividerBackgroundColor);
        }
        return this;
    }

    /**
     * 设置选中那个Tab
     *
     * @param index
     * @return
     */
    public BottomTabBar setCurrentTab(int index) {
        mTabHost.setCurrentTab(index);
        return this;
    }

    //=========================参数设置END===========================================

    //=========================工具类START========================================

    /**
     * dp转px
     *
     * @param dpValue
     * @return
     */
    private int dp2px(float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    //=========================工具类END===========================================
}
