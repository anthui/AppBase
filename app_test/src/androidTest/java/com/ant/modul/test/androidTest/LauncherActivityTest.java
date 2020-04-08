package com.ant.modul.test.androidTest;

import androidx.test.core.app.ActivityScenario;
import androidx.test.filters.LargeTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/3.
 * describe：
 */

@RunWith(JUnit4.class)
@LargeTest
public class LauncherActivityTest {

    //    @Rule
//    public ActivityScenario activityActivityTestRule = ActivityScenario.launch(LoginActivity.class);
    LoginActivity loginActivity;

    @Before
    public void init() {
        ActivityScenario<LoginActivity> launch = ActivityScenario.launch(LoginActivity.class);
        launch.onActivity(new ActivityScenario.ActivityAction<LoginActivity>() {
            @Override
            public void perform(LoginActivity activity) {
                loginActivity = activity;
//                activity.mLoginBtn.performClick();
            }
        });

    }

    @Test
    public void testLauncher() throws InterruptedException {

        Assert.assertNotNull(loginActivity);

//        onView(withId(R.id.btn_login)).perform(ViewActions.click());
//        loginActivity.mLoginBtn.performClick();
//        Thread.sleep(10000);
        Thread.sleep(1000);

        pressBack();

        //验证提示弹窗 是否弹出
        onView(withText("确认退出应用吗"))
                .inRoot(withDecorView(not(is(loginActivity.getWindow().getDecorView()))))
                .check(matches((isDisplayed())));
        Thread.sleep(1000);

        onView(withText("取消"))
                .inRoot(withDecorView(not(is(loginActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()))
                .perform(click());


        Thread.sleep(10000);


    }


}
