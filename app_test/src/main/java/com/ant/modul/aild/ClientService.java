package com.ant.modul.aild;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import androidx.annotation.Nullable;

import com.ant.antTest.SendMessageCallBack;
import com.ant.app_utils.LogUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/2.
 * describe：
 */
public class ClientService extends Service {

    private final ClientServiceStub mBinder = new ClientServiceStub();

    private class ClientServiceStub extends IRemoteClient.Stub {

        @Override
        public void sendMessage(String msg) throws RemoteException {
            LogUtil.e("进程 收到：" + msg);
        }

        @Override
        public void send(String msg, SendMessageCallBack callback, int expireDuration) throws RemoteException {
            LogUtil.e("进程 收到： 需要发送的消息  " + msg);

            callback.onSuccess("发送成功");
            callback.onFail(1, "anthui 发送失败");


        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
