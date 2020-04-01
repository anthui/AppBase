package com.ant.modul.test.javaTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/1.
 * describe：简单的Mockito操作
 */

@RunWith(MockitoJUnitRunner.class)
public class SimpleMockito {

    @Mock
    SimpleClass simpleClass;//模拟一个简单对象


    /**
     * mock 验证行为方法
     */

    @Test
    public void mockVerify() {

        ArrayList<String> mock = mock(ArrayList.class);
        mock.add("one");
        mock.add("2");
        mock.add("2");
        mock.add("hahah");
        mock.add("hahah");
        mock.add("hahah");

        mock.clear();
        //验证 集合是否提添加 one数据
        verify(mock).add("one");
//        verify(mock).add("two"); 没有添加 two，会报错
        //验证是否执行了 clear方法
        verify(mock).clear();


        MockDemo mockDemo = mock(MockDemo.class);
        mockDemo.mockFunTest();
        mockDemo.mockFunTest("00000");

        verify(mockDemo).mockFunTest(); //验证是否执行该方法
        verify(mockDemo).mockFunTest("00000");
//        verify(mockDemo).mockFunTest("哈哈");


        //===============================次数相关验证-=============================
        //验证执行次数 （添加1 被添加次数）
        verify(mock, times(1)).add("one");

        //验证是否执行（是否添加过xxx内容）
        verify(mock, never()).add("88888888888888");
        //添加过3  这里会报错
//        verify(list, never()).add("3");
        //最多只添加过1次， 0或者 1都正常过，其他报错
//        verify(mock, Mockito.atMostOnce()).add("2");
        verify(mock, Mockito.atMostOnce()).add("one");
        //最少添加过1次,没添加过的会报错，与never反比
        verify(mock, atLeastOnce()).add("hahah");
//        verify(mock, atLeastOnce()).add("ssss");
        //至少执行过 次数
        verify(mock, atLeast(2)).add("2");
        //最多只允许执行 次数
        verify(mock, atMost(2)).add("2");

    }

    @Mock
    ArrayList<String> list; //通过注解形式模拟出 list对象

    /**
     * Mock打桩测试
     */
    @Test
    public void stubbingTest() {

        list.add("1");
        list.add("12");
        list.add("3");
        list.add("4");
        list.add("1");
        //当执行
        when(list.get(0)).thenReturn("haha", "第二次返回", "第三次返回");
        //返回真是数据  ,由于是模拟，add也只是模拟，并未添加真实数据到集合中，所以返回的是IndexOutOfBoundException
//        when(list.get(2)).thenCallRealMethod();

        //当执行 get(1)方法，跑出 异常
//        when(list.get(1)).thenThrow(new RuntimeException("跑出异常"));
        //提前设置返回数据
        doReturn("提前设置返回").when(list).get(5);

        log(list.get(0));
        log(list.get(0));
        log(list.get(0));
        log(list.get(0));
        log(list.get(0));
        log(list.get(1));
        log(list.get(2));
        log(list.get(5));
        log(list.get(999));


    }

    /**
     * mock 参数匹配器
     */
    @Test
    public void mockEquals() {

        //list取出任何值 都是 element
        when(list.get(anyInt())).thenReturn("element");

        when(list.contains("element")).thenReturn(true);
//        when(list.contains(argThat(isValid()))).thenReturn(true);

        log("mockEquals", list.get(0));
        log("mockEquals", list.get(1));
        verify(list).add(argThat(someString -> someString.length() > 5));
//        verify(list).
//        verify(list).add((anyInt(), anyString(), eq("third argument"));

    }


    /**
     * 异常处理
     */
    @Test
    public void mockException() {

        doThrow(new RuntimeException()).when(list).clear();

        when(list.get(0)).thenThrow(new RuntimeException());
    }


    /**
     * 验证方法顺序
     */
    @Test
    public void mockOrder() {

        ArrayList mock = mock(ArrayList.class);
        mock.add("1");
        mock.add("2");
        mock.add("3");
        mock.add("4");
        InOrder inOrder = inOrder(mock);
        //验证添加的顺序，不需要 并列
//        inOrder.verify(mock).add("1");
        inOrder.verify(mock).add("1");
        inOrder.verify(mock).add("3");


        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("1");
        secondMock.add("2");
//两个不同类方法 执行的先后
        InOrder inOrder1 = inOrder(firstMock, secondMock);

        inOrder1.verify(firstMock).add("1");
        inOrder1.verify(secondMock).add("2");

        MockDemo mockTest = mock(MockDemo.class);
        mockTest.mockFunTest();
        mockTest.mockFunTest("haha");

        inOrder(mockTest).verify(mockTest).mockFunTest();
        inOrder(mockTest).verify(mockTest).mockFunTest("haha");

        MockDemo mock1 = mock(MockDemo.class);
        Student mock2 = mock(Student.class);
        mock1.mockFunTest(mock2);
        InOrder inOrder2 = inOrder(mock1, mock2);
        inOrder2.verify(mock2).mockList();
        inOrder2.verify(mock1).mockList();


    }


    private void log(String tag, String message) {

        System.out.println(tag + "   " + message);

    }

    private void log(String message) {

        System.out.println("测试数据  " + message);

    }

}
