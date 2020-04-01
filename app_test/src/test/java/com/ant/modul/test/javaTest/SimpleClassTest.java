package com.ant.modul.test.javaTest;


import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.startsWith;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/30.
 * describe：
 */
//@RunWith(MockitoJUnitRunner.class)
public class SimpleClassTest {

    @Mock
    SimpleClass simpleClassTest;
    @Rule //<--使用@Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void testMock() {
        SimpleClass mockSimple = Mockito.mock(SimpleClass.class);
        assertNotNull(mockSimple);

    }


    @Before
    public void init() {
//        simpleClassTest = new SimpleClass();
//        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void sizeTest() {
        MatcherAssert.assertThat("ds", startsWith("d"));

    }

    @Test(timeout = 1000)
    public void isTeenager() {
        Assert.assertFalse(simpleClassTest.isTeenager(3));

        MatcherAssert.assertThat("ds", startsWith("d"));
        MatcherAssert.assertThat("ds", endsWith("s"));
        List<String> actual = new ArrayList<>();
        actual.add("h");
        actual.add("h");
        actual.add("h0");

        MatcherAssert.assertThat(actual, hasItem("h"));
    }


    @Test
    public void mockVerify() {

        List<String> mock = mock(ArrayList.class);
        mock.add("one");
        mock.add("323");
        mock.add("ewe");
        mock.add("g432");
        mock.add("232");
        mock.clear();

        //验证mockedList.add("one")是否被调用，如果被调用则当前测试方法通过，否则失败
        verify(mock).add("one");
        //验证 mockedList.clear()是否被调用，如果被调用则当前测试方法通过，否则失败
        verify(mock).clear();

    }

    //打测试桩,设置返回值
    @Test
    public void returnMessage() {

        List mock = mock(List.class);
        mock.add("haha");
        when(mock.get(0)).thenReturn("first");
        doReturn("aaa").when(mock).get(1);
//        doThrow(new RuntimeException()).when(mock).clear();
        doNothing().when(mock).clear();
        mock.add("haha");

        System.out.println(mock.get(0));
        System.out.println(mock.get(199));
        System.out.println(mock.get(1));

        System.out.println(mock.size());
        mock.clear();
        System.out.println(mock.size());

        SimpleClass mock1 = mock(SimpleClass.class);
//        OngoingStubbing<String> hh = when(mock1).thenReturn(3).getNameById(2);
        when(mock1.getNameById(3)).thenReturn("33", "332", "44");

        doCallRealMethod().when(mock1).getNameById(1);
        System.out.println("  " + mock1.getNameById(3));
        System.out.println("  " + mock1.getNameById(3));
        System.out.println("  " + mock1.getNameById(3));
        System.out.println("44==  " + mock1.getNameById(1));


    }

    @Test
    public void formatViedoTime() {

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {

                return null;
            }
        }).when(simpleClassTest).setMessage("haha");


    }
}