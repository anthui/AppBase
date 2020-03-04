// IRemoteClient.aidl
package com.ant.modul.aild;
import com.ant.antTest.SendMessageCallBack;
// Declare any non-default types here with import statements

interface IRemoteClient {
  void sendMessage(String msg);
  oneway void send(in String msg, in SendMessageCallBack callback, in int expireDuration);

}
