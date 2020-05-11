package com.ant.kotlin.interfaces


/**
 * copyright：
 * @author：anthui
 * Version：1.0
 * creation date： 2020/4/16.
 * describe：
 */
interface OnMessageCallBack {
    var onSuccess: ((message: String) -> Unit)?

    var onFail: (() -> Unit)?
    fun loadMessage()

}

class HttpEnquen(var message: String) : OnMessageCallBack {
    override var onSuccess: ((message: String) -> Unit)? = null

    override var onFail: (() -> Unit)? = null

    init {
        //初始化完后 提供外部调用
    }

    override fun loadMessage() {
        onSuccess?.invoke("loadMessage")

    }


}