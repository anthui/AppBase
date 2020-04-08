package com.ant.modul.test.androidTest;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.ant.anttestlibrary.R;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;


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
    public ActivityTestRule<LoginActivity> activityRule = new ActivityTestRule<>(LoginActivity.class);

    //    @Test
    public void testLogin() throws InterruptedException {


//        ViewInteraction viewInteraction = onView(withId(R.id.btn_login));
//        viewInteraction.perform(ViewActions.click());

        //验证是否显示
        onView(withId(R.id.btn_login)).check(matches(isDisplayed()));

        //不输入任何内容，直接点击登录按钮
        onView(withId(R.id.btn_login)).perform(click());
//        onView(allOf(withId(R.id.btn_login), isDisplayed())).perform(click());
        Thread.sleep(1000);

        //只输入用户名
        onView(withId(R.id.edit_name)).perform(replaceText("哈哈"), closeSoftKeyboard());
        onView(withId(R.id.edit_name)).check(ViewAssertions.matches(withText(containsString("哈"))));

//        Matchers.hasItem()

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

        onView(withText("密码长度小于6"))
                .inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))));
//                .check(matches(isDisplayed()));

//        onView(withText("密码长度")).inRoot(withDecorView)


    }

    //    @Test
    public void testDialog() throws InterruptedException {


        pressBack();

        //验证有没有弹窗
        onView(withText(Matchers.containsString("确认退出应用吗")))
                .inRoot(RootMatchers.withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
//                .perform(click())
                .check(matches(ViewMatchers.isDisplayed()));
        Thread.sleep(2000);

        onView(withText("确认")).inRoot(withDecorView(not(is(activityRule.getActivity().getWindow().getDecorView()))))
                .perform(click());

        Thread.sleep(2000);

        Assert.assertTrue(activityRule.getActivity().isFinishing());

        onView(withId(R.id.tv_content)).perform(ViewActions.clearText());
//        onView(allOf(withId(R.id.tv_call), withText("hahah"))).perform(typeText("hahha"), click());

    }


    @Test
    public void testScro() throws InterruptedException {

//        onView(withId(R.id.tv_content)).perform( typeText("abx"));
//        onView(allOf(withId(R.id.tv_content), withText("测试滑动"))).perform(click());
//        onView(withId(R.id.scrollView)).perform(scrollTo());

        onView(withId(R.id.tv_content)).check(matches(isDisplayed())).perform(clearText()).perform(ViewActions.typeText("haha"),closeSoftKeyboard());
        Thread.sleep(10000);


    }
}