package com.ant.modul.aild;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ant.antTest.SendMessageCallBack;
import com.ant.anttestlibrary.R;
import com.ant.app_base.BaseActivity;
import com.ant.app_utils.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/2.
 * describe：
 */
public class ClientActivity extends BaseActivity {
    @BindView(R.id.tv_send)
    Button tvSend;
    @BindView(R.id.tv_call)
    TextView tvCall;
    private IRemoteClient mClient;

    @Override
    public int getMainContentViewId() {
        return R.layout.activity_aild;
    }


    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mClient = IRemoteClient.Stub.asInterface(service);
            try {
                mClient.sendMessage("链接成功 ：发送相关数据");

            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void initData() {

    }

    @Override
    public void initComponents(Bundle savedInstanceState, View rootView) {

        Intent intent = new Intent(mContext, ClientService.class);
        mContext.bindService(intent, connection, BIND_AUTO_CREATE);
    }


    @OnClick(R.id.tv_send)
    public void onViewClicked() {
        try {
            mClient.send("哈哈哈", new SendMessageCallBack.Stub() {
                @Override
                public void onSuccess(String msg) throws RemoteException {

                    LogUtil.e("msg== " + msg);
                }

                @Override
                public void onFail(int code, String errMessage) throws RemoteException {
                    LogUtil.e("msg== " + errMessage);

                }
            }, 1);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
