package com.ant.app_views.antBanner;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.ant.app_base.R;
import com.ant.app_views.RoundImageView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date：2018/1/31
 * describe： 广告轮播空间  banner
 * 支持 3个轮播动画
 */
public class AntBanner extends RelativeLayout {
    private static final String TAG = AntBanner.class.getSimpleName();

    private Context mContext;
    /**
     * 布局参数
     */

    private static final int RMP = LayoutParams.MATCH_PARENT;
    private static final int RWC = LayoutParams.WRAP_CONTENT;
    private static final int LWC = LinearLayout.LayoutParams.WRAP_CONTENT;
    /**
     * 循环轮播的Viewpager
     */
    private SLooperViewPager mViewPager;


    //下面这两个控件，存放到一个相对布局中，由于不需要设置成员变量，故此没写
    /**
     * 轮播控件的提示文字
     */
    private TextView mTipTextView;
    /**
     * 提示文字的大小
     */
    private int mTipTextSize;

    /**
     * 提示文字的颜色，默认是白色
     */
    private int mTipTextColor = Color.WHITE;

    /**
     * 指示器存放的容器（小白点）
     */
    private LinearLayout mPointContainerLl;
    /**
     * 指示器 选择器（小白点）
     */
    private int mPointDrawableResId = R.drawable.selector_banner_point;

    /**
     * 点在容器中的layout的属性
     */
    private int mPointGravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;
    private int mPointLeftRightMargin;

    private int mPointTopBottomMargin;
    private int mPointContainerLeftRightPadding;

    /**
     * 存放TipTextView和mPointContainerLl的相对布局的背景资源Id；
     */
    private Drawable mPointContainerBackgroundDrawable;

    /**
     * 存放轮播信息的数据集合
     */
    protected List mData = new ArrayList<>();

    /**
     * 自动播放的间隔
     */
    private int mAutoPlayInterval = 3;

    /**
     * 页面切换的时间（从下一页开始出现，到完全出现的时间）
     */
    private int mPageChangeDuration = 300;
    /**
     * 是否正在播放
     */
    private boolean mPlaying = false;

    /**
     * 当前的页面的位置
     */
    protected int currentPosition;

    /**
     * Banner控件的适配器
     */
    private BannerAdapter mBannerAdapter;

    /**
     * 任务执行器
     */
    protected ScheduledExecutorService mExecutor;

    private int mViewPagerPadding = 0;


    /**
     * 播放下一个执行器
     */
    private Handler mPlayHandler = new PlayHandler(this);
    /**
     * 是否限制指示器
     */
    private boolean showPoint = true;


    /**
     * 距离ViewPage 左右间距  设置显示三个的时候才有效果showThree=true
     */
    private int viewPageMargeLeft_Right;
    /**
     * 距离ViewPage 上下间距  设置显示三个的时候才有效果showThree=true
     */
    private int viewPageMargeTop_Bottom;

    private boolean showThree = false;


    //是否自动滚动
    private boolean autoScro = true;

    public AntBanner(Context context) {
        this(context, null);
    }

    public AntBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AntBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化默认属性 主要初始化小白点
        initDefaultAttrs(context);

        //初始化自定义属性
        initCustomAttrs(context, attrs);

        //控件初始化
        initView(context);
    }

    private void initDefaultAttrs(Context context) {

        //默认点指示器的左右Margin3dp
        mPointLeftRightMargin = dp2px(context, 3);
        //默认点指示器的上下margin为6dp
        mPointTopBottomMargin = dp2px(context, 6);
        //默认点容器的左右padding为10dp
        mPointContainerLeftRightPadding = dp2px(context, 10);
        //默认指示器提示文字大小8sp
        mTipTextSize = sp2px(context, 8);
        //默认指示器容器的背景图片
        mPointContainerBackgroundDrawable = new ColorDrawable(Color.parseColor("#33000000"));
        //ViewPage左右间距 默认50do
        viewPageMargeLeft_Right = dp2px(context, 20);
        //ViewPage上下间距 默认为0
        viewPageMargeTop_Bottom = RMP;
    }

    private int dp2px(Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.getResources().getDisplayMetrics());
    }

    private int sp2px(Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * 初始化自定义属性
     *
     * @param context context
     * @param attrs   attrs
     */
    private void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AntBanner);
        final int N = typedArray.getIndexCount();
        for (int i = 0; i < N; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.AntBanner_banner_pointDrawable) {
            //指示器点的样式资源id
            mPointDrawableResId = typedArray.getResourceId(attr, R.drawable.selector_banner_point);
        } else if (attr == R.styleable.AntBanner_banner_pointContainerBackground) {
            //指示器容器背景样式
            mPointContainerBackgroundDrawable = typedArray.getDrawable(attr);

        } else if (attr == R.styleable.AntBanner_banner_pointLeftRightMargin) {
            //指示器左右边距
            mPointLeftRightMargin = typedArray.getDimensionPixelSize(attr, mPointLeftRightMargin);
        } else if (attr == R.styleable.AntBanner_banner_pointContainerLeftRightPadding) {
            //指示器容器的左右padding
            mPointContainerLeftRightPadding = typedArray.getDimensionPixelSize(attr, mPointContainerLeftRightPadding);
        } else if (attr == R.styleable.AntBanner_banner_pointTopBottomMargin) {

            //指示器的上下margin
            mPointTopBottomMargin = typedArray.getDimensionPixelSize(attr, mPointTopBottomMargin);
        } else if (attr == R.styleable.AntBanner_banner_pointGravity) {
            //指示器在容器中的位置属性
            mPointGravity = typedArray.getInt(attr, mPointGravity);
        } else if (attr == R.styleable.AntBanner_banner_pointAutoPlayInterval) {
            //轮播的间隔
            mAutoPlayInterval = typedArray.getInteger(attr, mAutoPlayInterval);
        } else if (attr == R.styleable.AntBanner_banner_pageChangeDuration) {
            //页面切换的持续时间
            mPageChangeDuration = typedArray.getInteger(attr, mPageChangeDuration);
        } else if (attr == R.styleable.AntBanner_banner_tipTextColor) {
            //提示文字颜色
            mTipTextColor = typedArray.getColor(attr, mTipTextColor);
        } else if (attr == R.styleable.AntBanner_banner_tipTextSize) {
            //提示文字大小
            mTipTextSize = typedArray.getDimensionPixelSize(attr, mTipTextSize);
        } else if (attr == R.styleable.AntBanner_banner_ViewPager_padding) {
            //viewPager的padding
            mViewPagerPadding = typedArray.getDimensionPixelSize(attr, mViewPagerPadding);

        } else if (attr == R.styleable.AntBanner_banner_pointShow) {
            //是否显示 指示器，只做显示 隐藏，并不影响其他功能
            showPoint = typedArray.getBoolean(attr, true);
        } else if (attr == R.styleable.AntBanner_banner_viewPage_margin_leftRight) {
            //viewPager的padding
            viewPageMargeLeft_Right = (int) typedArray.getDimension(attr, 0);
        } else if (attr == R.styleable.AntBanner_banner_viewPageHeight) {
            viewPageMargeTop_Bottom = (int) typedArray.getDimension(attr, RMP);

        } else if (attr == R.styleable.AntBanner_banner_showThree) {
            showThree = typedArray.getBoolean(attr, false);
        } else if (attr == R.styleable.AntBanner_banner_auto_scro) {

            autoScro = typedArray.getBoolean(attr, true);
        }

    }

    /**
     * 控件初始化
     *
     * @param context context
     */
    private void initView(Context context) {
        mContext = context;

        //初始化ViewPager
        mViewPager = new SLooperViewPager(context, showThree);

        //预加载4个
        mViewPager.setOffscreenPageLimit(4);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RMP, viewPageMargeTop_Bottom);
        //是否显示三个页面
        if (showThree) {
            setClipChildren(false);
            //开放方法外部使用
//            mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
            layoutParams.setMargins(viewPageMargeLeft_Right, 0, viewPageMargeLeft_Right, 0);

        }
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        addView(mViewPager, layoutParams);

        //修正banner页面切换时间
        mPageChangeDuration = mPageChangeDuration > (mAutoPlayInterval * 1000) ? (mAutoPlayInterval * 1000) : mPageChangeDuration;

        // 设置banner轮播的切换时间
        ViewPagerScroller pagerScroller = new ViewPagerScroller(mContext);
        pagerScroller.changScrollDuration(mViewPager, mPageChangeDuration);


        //创建指示器容器的相对布局
        RelativeLayout indicatorContainerRl = new RelativeLayout(context);
        if (!showPoint) {
            indicatorContainerRl.setVisibility(GONE);
        }

        //设置指示器容器的背景
        indicatorContainerRl.setBackground(mPointContainerBackgroundDrawable);
//
        //设置指示器容器Padding
        indicatorContainerRl.setPadding(mPointContainerLeftRightPadding, 0, mPointContainerLeftRightPadding, 0);
        //初始化指示器容器的布局参数
        LayoutParams indicatorContainerLp = new LayoutParams(RMP, RWC);
        // 设置指示器容器内的子view的布局方式
        if ((mPointGravity & Gravity.VERTICAL_GRAVITY_MASK) == Gravity.TOP) {
            indicatorContainerLp.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        } else {
            indicatorContainerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        }
        //将指示器容器添加到父View中
        addView(indicatorContainerRl, indicatorContainerLp);


        //初始化存放点的容器线性布局
        mPointContainerLl = new LinearLayout(context);
        //设置点容器布局的id
        mPointContainerLl.setId(R.id.banner_pointContainerId);
        //设置线性布局的方向
        mPointContainerLl.setOrientation(LinearLayout.HORIZONTAL);
        //设置点容器的布局参数
        LayoutParams pointContainerLp = new LayoutParams(RWC, RWC);
        //将点容器存放到指示器容器中
        indicatorContainerRl.addView(mPointContainerLl, pointContainerLp);


        //初始化tip的layout尺寸参数，高度和点的高度一致
        LayoutParams tipLp = new LayoutParams(RMP, getResources().getDrawable(mPointDrawableResId).getIntrinsicHeight() + 2 * mPointTopBottomMargin);
        mTipTextView = new TextView(context);
        mTipTextView.setGravity(Gravity.CENTER_VERTICAL);
        mTipTextView.setSingleLine(true);
        mTipTextView.setEllipsize(TextUtils.TruncateAt.END);
        mTipTextView.setTextColor(mTipTextColor);
        mTipTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTipTextSize);
        //将TieTextView存放于指示器容器中
        indicatorContainerRl.addView(mTipTextView, tipLp);
        int horizontalGravity = mPointGravity & Gravity.HORIZONTAL_GRAVITY_MASK;
        // 处理圆点容器位于指示器容器的左边、右边还是水平居中
        if (horizontalGravity == Gravity.LEFT) {
            pointContainerLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            //提示文字设置在点容器的右边
            tipLp.addRule(RelativeLayout.RIGHT_OF, R.id.banner_pointContainerId);
            mTipTextView.setGravity(Gravity.CENTER_VERTICAL | Gravity.RIGHT);
        } else if (horizontalGravity == Gravity.RIGHT) {
            pointContainerLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            tipLp.addRule(RelativeLayout.LEFT_OF, R.id.banner_pointContainerId);
        } else {
            pointContainerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            tipLp.addRule(RelativeLayout.LEFT_OF, R.id.banner_pointContainerId);
        }
    }


    /**
     * 设置切换动画
     */
    public void setPageTransformer(ViewPager.PageTransformer pageTransformer) {
        mViewPager.setPageTransformer(true, pageTransformer);

    }

    /**
     * 初始化点
     * 这样的做法，可以使在刷新获数据的时候提升性能
     */
    private void initPoints() {
        //获取容器中原有点的数量
        int childCount = mPointContainerLl.getChildCount();
        //获取目标点的数据量
        int dataSize = mData.size();
        //获取增加获取删减点的数量
        int offset = dataSize - childCount;
        if (offset == 0)
            return;
        if (offset > 0) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LWC, LWC);
            lp.setMargins(mPointLeftRightMargin, mPointTopBottomMargin, mPointLeftRightMargin, mPointTopBottomMargin);
            ImageView imageView;
            for (int i = 0; i < offset; i++) {
                imageView = new ImageView(getContext());
                imageView.setLayoutParams(lp);
                imageView.setImageResource(mPointDrawableResId);
                imageView.setEnabled(false);
                mPointContainerLl.addView(imageView);
            }
            mPointContainerLl.getChildAt(0).setEnabled(true);
            return;
        }
        if (offset < 0) {
            mPointContainerLl.removeViews(dataSize, -offset);
        }
    }


    private final class ChangePointListener extends SLooperViewPager.SimpleOnPageChangeListener {
        @Override
        public void onPageSelected(int position) {
            currentPosition = position % mData.size();
            switchToPoint(currentPosition);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (mTipTextView != null) {
                if (positionOffset > 0.5) {
                    mBannerAdapter.selectTips(mTipTextView, currentPosition);
                    mTipTextView.setAlpha(positionOffset);
                } else {
                    mTipTextView.setAlpha(1 - positionOffset);
                    mBannerAdapter.selectTips(mTipTextView, currentPosition);
                }
            }
        }
    }

    /**
     * 将点切换到指定的位置
     * 就是将指定位置的点设置成Enable
     *
     * @param newCurrentPoint 新位置
     */
    private void switchToPoint(int newCurrentPoint) {
        for (int i = 0; i < mPointContainerLl.getChildCount(); i++) {
            mPointContainerLl.getChildAt(i).setEnabled(false);
        }
        mPointContainerLl.getChildAt(newCurrentPoint).setEnabled(true);

        if (mTipTextView != null) {
            mBannerAdapter.selectTips(mTipTextView, currentPosition);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                pauseScroll();
                break;
            case MotionEvent.ACTION_UP:
                goScroll();
                break;
            case MotionEvent.ACTION_CANCEL:
                goScroll();
                break;
        }
        return super.dispatchTouchEvent(ev);
    }


    /**
     * set viewPage change time
     *
     * @param duration time
     */
    @SuppressWarnings("unused")
    public void setPageChangeDuration(int duration) {
        mPageChangeDuration = duration;
    }

    /**
     * goScroll next
     *
     * @param position position
     */
    private void scrollToNextItem(int position) {
        position++;
        mViewPager.setCurrentItem(position, true);
    }


    /**
     * viewPager inner adapter
     */
    private final class InnerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {


            View itemView = mBannerAdapter.getBannerItemView(position);


            if (itemView == null) {
                RoundImageView view = new RoundImageView(mContext);
                view.setCurrMode(RoundImageView.MODE_ROUND);
                view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                mBannerAdapter.setImageViewSource(view, position);
                itemView = view;
            }

            itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onVpItemClickListener != null) {
                        onVpItemClickListener.onItemClick(position);
                    }
                }
            });
            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }


    private OnBannerItemClickListener onVpItemClickListener;

    /**
     * 设置viewPage的Item点击监听器
     *
     * @param listener listener
     */
    public void setOnBannerItemClickListener(OnBannerItemClickListener listener) {
        this.onVpItemClickListener = listener;
    }

    public interface OnBannerItemClickListener {
        void onItemClick(int position);
    }


    /**
     * 方法使用状态 ：viewpager处于暂停的状态
     * 开始滚动
     */
    public void goScroll() {

        if (!autoScro) {
            return;
        }
        if (!isValid()) {
            return;
        }


        if (mPlaying) {
            return;
        } else {
            pauseScroll();
            mExecutor = Executors.newSingleThreadScheduledExecutor();
            //command：执行线程
            //initialDelay：初始化延时
            //period：两次开始执行最小间隔时间
            //unit：计时单位
            mExecutor.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    mPlayHandler.obtainMessage().sendToTarget();
                }
            }, mAutoPlayInterval, mAutoPlayInterval, TimeUnit.SECONDS);
            mPlaying = true;
        }
    }


    /**
     * 暂停滚动
     */
    public void pauseScroll() {
        if (mExecutor != null) {
            mExecutor.shutdown();
            mExecutor = null;
        }
        mPlaying = false;
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        if (visibility == VISIBLE) {
            goScroll();
        } else if (visibility == INVISIBLE) {
            pauseScroll();
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        pauseScroll();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        goScroll();
    }

    /**
     * 判断控件是否可用
     *
     * @return
     */
    private boolean isValid() {
        if (mViewPager == null) {
            Log.e(TAG, "ViewPager is not exist!");
            return false;
        }
        if (mData == null || mData.size() == 0) {
            Log.e(TAG, "DataList must be not empty!");
            return false;
        }
        return true;
    }

    /**
     * 设置数据的集合
     */
    private void setSource() {
        List list = mBannerAdapter.getDataList();
        if (list == null) {
            Log.d(TAG, "setSource: list==null");
            return;
        }
        this.mData = list;
        setAdapter();
    }

    /**
     * 给viewpager设置适配器
     */
    private void setAdapter() {
        mViewPager.setAdapter(new InnerPagerAdapter());
        mViewPager.addOnPageChangeListener(new ChangePointListener());
    }

    public void setBannerAdapter(BannerAdapter adapter) {
        mBannerAdapter = adapter;
        setSource();
    }


    /**
     * 通知数据已经放生改变
     */
    public void notifyDataHasChanged() {

        notifyDataHasChanged(true);
    }

    /**
     * 通知数据已经放生改变
     */


    public void notifyDataHasChanged(boolean autoScro) {
        this.autoScro = autoScro;
        initPoints();
        mViewPager.getAdapter().notifyDataSetChanged();
        mViewPager.setCurrentItem(0, true);
        if (mData.size() > 1 && autoScro)
            goScroll();
    }


    /**
     * 静态内部类，防止发生内存泄露
     */
    private static class PlayHandler extends Handler {
        WeakReference<AntBanner> mWeakBanner;

        public PlayHandler(AntBanner banner) {
            this.mWeakBanner = new WeakReference<AntBanner>(banner);
        }

        @Override
        public void handleMessage(Message msg) {
            AntBanner weakBanner = mWeakBanner.get();
            if (weakBanner != null)
                weakBanner.scrollToNextItem(weakBanner.currentPosition);
        }
    }


}
