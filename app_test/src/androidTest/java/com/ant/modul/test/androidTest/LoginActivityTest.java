package com.ant.modul.test.androidTest;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.ant.anttestlibrary.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;


/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/3/30.
 * describe：
 */
@RunWith(AndroidJUnit4.class)
//@LargeTest
public class LoginActivityTest {
    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule = new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testLogin() throws InterruptedException {
        //验证是否显示
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));

        //不输入任何内容，直接点击登录按钮
        onView(withId(R.id.btn_login)).perform(click());
//        onView(allOf(withId(R.id.btn_login), isDisplayed())).perform(click());
        Thread.sleep(1000);

        //只输入用户名
        onView(withId(R.id.edit_name)).perform(typeText("admin"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.edit_name)).perform(clearText());
        Thread.sleep(1000);

        //同时输入用户名和密码，但是密码格式不正确
        onView(withId(R.id.edit_name)).perform(typeText("admin"));
        onView(withId(R.id.edit_password)).perform(typeText("123"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        onView(withId(R.id.edit_name)).perform(clearText());
        onView(withId(R.id.edit_password)).perform(clearText());
        Thread.sleep(1000);

        //输入正确的用户名和密码
        onView(withId(R.id.edit_name)).perform(typeText("admin"));
        onView(withId(R.id.edit_password)).perform(typeText("123456"), closeSoftKeyboard());
        onView(withId(R.id.btn_login)).perform(click());
        Thread.sleep(1000);

        //验证内容
        onView(withId(R.id.btn_login)).check(matches(withText("登录成功")));
        onView(withId(R.id.edit_name)).check(matches(withText("admin")));
        onView(withId(R.id.edit_password)).check(matches(withText("123456")));
        Thread.sleep(1000);

    }

}