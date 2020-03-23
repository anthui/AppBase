package com.ant.kotlin.activity

import com.ant.app_utils.LogUtil


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/11.
 * describe：
 */

class User(var name: String, var age: Int) {
    init {
        LogUtil.e("msg========= " + name + " age== " + age)
    }

    constructor() : this("name", 1) {

    }

    init {
        LogUtil.e("msg2222222------------" + name)
    }

    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }


}

class Student(var age: Int) {

    init {
        LogUtil.e("msg======================================1")

    }

    init {
        LogUtil.e("msg======================================2")

    }


    init {

        LogUtil.e("msg======================================3  " + this.age)
    }

    //
//    constructor(age: Int) {
//        this.age = age
//        LogUtil.e("msg======================age== " + age)
//
//
//    }
    init {

        LogUtil.e("msg======================================3  " + this.age)
    }



}
