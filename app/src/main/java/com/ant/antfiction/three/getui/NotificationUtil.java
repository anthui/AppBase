package com.ant.antfiction.three.getui;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.view.View;

import com.ant.antfiction.R;
import com.ant.utils.StringUtil;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2018/11/13.
 * describe：
 */
public class NotificationUtil {

    public static String NOTIFY_CHANNEL_ID_DEFAULT = "notify";
    public static String NOTIFY_CHANNEL_NAME_DEFAULT = "消息通知";

    public final static int TYPE_SOUND = 0;//生意
    public final static int TYPE_VIBRATOR = 1;//震动
    public final static int TYPE_NONE = 2;//静音
    public final static int TYPE_ALL = 3;//所有 默认

    /**
     * 创建通知渠道 创建默认渠道 8.0后才支持
     */
    public static void onCreateChannel(Context mContext) {
        // onCreateChannel(mContext, "dd", "dsjhuiehw");
        onCreateChannel(mContext, NOTIFY_CHANNEL_ID_DEFAULT, NOTIFY_CHANNEL_NAME_DEFAULT);
    }

    public static void onCreateChannel(Context mContext, String channelId, String channelName) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
            channel.setShowBadge(true);
            channel.setLockscreenVisibility(View.VISIBLE);
            channel.setBypassDnd(true);
            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(
                    Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);

        }
    }

    //弹窗类型
    static int notify_type = Notification.DEFAULT_ALL;

    public static void setNotifyType(int type, Context mContext) {

        notify_type = type;

    }

    private static int notifyId = 1;


    public static void notifyChanelMessage(Context mContext, String channelId, String title, String message, Intent classIntent) {


        if (StringUtil.isEmpty(title) || StringUtil.isEmpty(message)) {
            return;
        }

        NotificationManager manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        }
        NotificationCompat.Builder notification = new NotificationCompat.Builder(mContext, channelId)

                .setContentTitle(title)
                .setContentText(message)
                //.setFullScreenIntent(null, true)
                //   .setTimeoutAfter(1000 * 1000)

                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL);
//           .setDefaults(Notification.DEFAULT_LIGHTS);

        notifyId++;

        //点击是 是否跳转页面
        if (classIntent != null) {
            if (mContext instanceof Activity) {

            } else {
            }
            PendingIntent pending = PendingIntent.getActivity(mContext, 11, classIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pending);
            manager.notify(notifyId, notification.build());
        } else {
            //   LogUtil.e("======================================");
            Notification build = notification.build();
            manager.notify(notifyId, build);
        }
    }

    public static void notifyChanelMessage(Context mContext, String title, String message, Intent classIntent) {
        notifyChanelMessage(mContext, NOTIFY_CHANNEL_ID_DEFAULT, title, message, classIntent);
    }


    public static void notifyData(Context paramContext, GeTuiBean geTuiBean) {
        if (geTuiBean == null) {
            return;
        }
//        switch (geTuiBean.getType()) {
//            case 2:
//                ZhuanSuccessListener.getZhuanListener().ontifySuccess(geTuiBean.getSource_data());
//                Intent localIntent = new Intent(paramContext, TransaceDetailActivity.class);
//                localIntent.putExtra(IntentConfig.OBJECT_DATA, geTuiBean.getSource_data());
//                notifyChanelMessage(paramContext, geTuiBean.getTitle(), geTuiBean.getContent(), localIntent);
//
//                break;
//
//
//            default:
//                return;
//        }

    }

}
