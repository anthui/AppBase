package com.ant.antfiction.three.getui;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.Nullable;

import com.ant.antfiction.base.http.HttpManager;
import com.ant.base.db.SpManager;
import com.ant.http.Bean.ErrBean;
import com.ant.http.Bean.TokenInfoBean;
import com.ant.http.listener.OnHttpRequestListener;
import com.ant.utils.JsonUtil;
import com.ant.utils.LogUtil;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.message.GTCmdMessage;
import com.igexin.sdk.message.GTNotificationMessage;
import com.igexin.sdk.message.GTTransmitMessage;


/**
 * 继承 GTIntentService 接收来自个推的消息, 所有消息在线程中回调, 如果注册了该服务, 则务必要在 AndroidManifest中声明, 否则无法接受消息<br>
 * onReceiveMessageData 处理透传消息<br>
 * onReceiveClientId 接收 cid <br>
 * onReceiveOnlineState cid 离线上线通知 <br>
 * onReceiveCommandResult 各种事件处理回执 <br>
 */
public class DemoIntentService extends GTIntentService {

    @Override
    public void onReceiveServicePid(Context context, int pid) {
    }

    //渗透消息 payload
    @Override
    public void onReceiveMessageData(Context context, GTTransmitMessage msg) {
        LogUtil.e("msg", "onReceiveMessageData: msg==" + msg.getMessageId());
        //  Log.e("msg", "onReceiveMessageData: ==" + msg.getPayloadId() + msg.getPayload().toString());
        String appid = msg.getAppid();
        String taskid = msg.getTaskId();
        String messageid = msg.getMessageId();
        String pkg = msg.getPkgName();
        String cid = msg.getClientId();
        //  Log.e("msgs", "onReceiveMessageData: taskid==" + taskid);
        LogUtil.e("msgs", "onReceiveMessageData: cid" + msg.getClientId());

        // boolean result = PushManager.getInstance().sendFeedbackMessage(context, taskid, messageid, 90001);
        //  Log.d(TAG, "call sendFeedbackMessage = " + (result ? "success" : "failed"));
        byte[] payload = msg.getPayload();
        if (payload != null) {
            String data = new String(payload);
            LogUtil.e("onReceiveMessageData:" + data);
            // notificationMessage(data);

            try {
                //  GeTuiBean geTuiBean = new Gson().fromJson(data, GeTuiBean.class);

                GeTuiBean geTuiBean = JsonUtil.toObject(data, GeTuiBean.class);
                //   Log.e("msg", "onReceiveMessageData: ===" + data);
                //   Log.e("msg", "onReceiveMessageData: title" + geTuiBean.getBody());
                notificationMessage(geTuiBean);
            } catch (Exception e) {
                //   Log.e("msg", "onReceiveMessageData: 个推后台参数传递错误");
                LogUtil.e("个推后台参数格式传递错误" + data);
                LogUtil.e("msg", e);
            }
            //  Log.e("msg", "onReceiveMessageData: 传递的参数===" + data);
            //  Log.e("msg", ": 整体getMessageId===" + msg.getMessageId() + "\n getPayload===" + msg.getPayload() + " \ngetPayloadId===" + msg.getPayloadId() + "\ngetPayload==" + msg.getPayload());
            ///   notificationMessage("shake");
        }
    }

    @Override
    public void onReceiveClientId(Context context, String clientid) {
        //Log.e("msgs", "onReceiveClientId -> " + "clientid = " + clientid);
        LogUtil.e(clientid);
        SpManager.saveClinetId(context, clientid);
        HttpManager.getHttpAction().upClientId(clientid, new OnHttpRequestListener() {
            @Override
            public void onSuccess(Object o, TokenInfoBean tokenInfoBean) {

            }

            @Override
            public void onFailure(ErrBean errBean) {

            }
        });
        //   UserSp.addPush(context);
    }

    @Override
    public void onReceiveOnlineState(Context context, boolean online) {
        LogUtil.e("msg", "onReceiveOnlineState: " + online);
    }

    @Override
    public void onReceiveCommandResult(Context context, GTCmdMessage cmdMessage) {
        LogUtil.e("msg", "onReceiveCommandResult: ===" + cmdMessage.getAppid());
    }

    @Override
    public void onNotificationMessageArrived(Context context, GTNotificationMessage gtNotificationMessage) {

    }

    @Override
    public void onNotificationMessageClicked(Context context, GTNotificationMessage gtNotificationMessage) {

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        //  Log.e("msg", "onStartCommand: =================");
        return super.onStartCommand(intent, flags, startId);
    }

    public void notificationMessage(GeTuiBean data) {
        NotificationUtil.notifyData(getApplicationContext(), data);

        // Intent classIntent = new Intent(getApplicationContext(), MessageActivity.class);
        //    NotificationUtil.notifyChanelMessage(getApplicationContext(), "lalala", "这是西欧阿西提", null);

//
    }
}
