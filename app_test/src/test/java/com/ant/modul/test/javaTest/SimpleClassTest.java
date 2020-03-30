package com.ant.modul.test.javaTest;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/30.
 * describe：
 */
@RunWith(JUnit4.class)
public class SimpleClassTest {
    private SimpleClass simpleClass;

    @Before
    public void setUp() throws Exception {
        simpleClass = new SimpleClass();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void isTeenager() {
        Assert.assertSame("hhahah", simpleClass, new SimpleClass());

        Assert.assertFalse(simpleClass.isTeenager(20));
        Assert.assertTrue(simpleClass.isTeenager(14));
        Assert.assertTrue(simpleClass.isTeenager(14));
//        Assert.assertEquals(simpleClass.add(3, 2), 6);

    }

    @Test
    public void add() {
        Assert.assertEquals(simpleClass.add(3, 2), 5);
        Assert.assertNotEquals(simpleClass.add(3, 2), 4);
    }

    @Test
    public void getNameById() {
        Assert.assertEquals(simpleClass.getNameById(1), "小明");
        Assert.assertEquals(simpleClass.getNameById(2), "小红");
        Assert.assertEquals(simpleClass.getNameById(10), "");
    }

    @Test
    public void formatViedoTime() {
        String message = simpleClass.formatViedoTime("" + System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");

//        Assert.s

    }
}
