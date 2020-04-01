package com.ant.modul.test.javaTest;

import com.ant.app_utils.LogUtil;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/1.
 * describe：Mock测试实体
 */
public class MockDemo {


    /*
     *验证是否执行该方法
     * */
    public void mockFunTest(Student student) {
        LogUtil.e("执行该方法");

        student.mockList();
        mockList();
    }
    /*
     *验证是否执行该方法
     * */
    public void mockFunTest() {
        LogUtil.e("执行该方法");

        new Student().mockList();
        mockList();
    }

    public void mockList() {
    }

    /*
     *验证是否执行该方法
     * */
    public void mockFunTest(String message) {
        LogUtil.e("执行该方法" + message);
    }

}
