//package com.ant.user;
//
//import android.content.Context;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.ant.user.bean.UserBean;
//import com.ant.utils.ImageLoadUtil;
//import com.ant.utils.StringUtil;
//
///**
// * copyright：
// * author：anthui
// * Version：1.0
// * creation date： 2018/10/23.
// * describe：
// */
//public class UserHelper {
//
//    //设置头像
//    public static void setHead(Context mContext, ImageView imageView) {
//
//        UserBean user = UserSp.getUser(mContext);
//
//        if (user != null) {
//            ImageLoadUtil.LoadImgNormal(mContext, user.getAvatar(), imageView);
//        }
//    }
//
//    public static void setNikeName(Context mContext, TextView textView) {
//
//        UserBean user = UserSp.getUser(mContext);
//        if (user != null) {
//            if (!StringUtil.isEmpty(user.getNickname())) {
//
//                textView.setText(user.getNickname());
//                return;
//            }
//            if (!StringUtil.isEmpty(user.getUser_name())) {
//                textView.setText(user.getUser_name());
//                return;
//            }
//            textView.setText(user.getTelephone());
//        }
//    }
//
//    public static void setPhoneNum(Context mContext, TextView textView, String num) {
//
//
//        if (textView == null || StringUtil.isEmpty(num)) {
//            return;
//        }
//
//        if (num.length() >= 7) {
//
//            String start = num.substring(0, 3);
//            String end = num.substring(num.length() - 4, num.length());
//            textView.setText(start + "****" + end);
//
//        } else {
//            textView.setText(num);
//
//        }
//    }
//}
