// SendMessageCallBack.aidl
package com.ant.antTest;

// Declare any non-default types here with import statements

interface SendMessageCallBack {

void onSuccess(String msg);
void onFail(int code,String errMessage);

}
