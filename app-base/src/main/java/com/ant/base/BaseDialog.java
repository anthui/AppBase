package com.ant.base;

/**
 * copyright：haoxin
 * author：anthui
 * Version：1.0
 * creation date：2018/2/2
 * describe：所有弹框
 */
//
//public abstract class BaseDialog extends Dialog {
//    protected BaseDialog(@NonNull Context context) {
//
//        super(context);
//        setCancelable(true);
//        //    setWindowSize();
//        setContentView(getMainContentViewId());
//        setCanceledOnTouchOutside(true);
//
//        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        initComponents();
//    }
//
//
//    /**
//     * 初始化UI组件及数据
//     */
//    protected abstract void initComponents();
//
//    /**
//     * 布局ID
//     */
//    protected abstract int getMainContentViewId();
//
//
////    @Override
////    public boolean onTouchEvent(MotionEvent event) {
////        LogUtil.e("onTouchEvent============");
////        /* 触摸外部弹窗 */
////        if (isOutOfBounds(getContext(), event)) {
////            //  onTouchOutside();
////            LogUtil.e("onTouchEvent=====33333333333333=======");
////
////            cancel();
////        }
////        return super.onTouchEvent(event);
////    }
////    // protected abstract void onTouchOutside();
////
////    private boolean isOutOfBounds(Context context, MotionEvent event) {
////        final int x = (int) event.getX();
////        final int y = (int) event.getY();
////        final int slop = ViewConfiguration.get(context).getScaledWindowTouchSlop();
////        final View decorView = getWindow().getDecorView();
////        return (x < -slop) || (y < -slop) || (x > (decorView.getWidth() + slop))
////                || (y > (decorView.getHeight() + slop));
////    }
//
//}
