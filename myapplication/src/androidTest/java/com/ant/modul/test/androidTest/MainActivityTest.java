package com.ant.modul.test.androidTest;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import com.ant.myapplication.MainActivity;
import com.ant.myapplication.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/2.
 * describe：
 */
@RunWith(JUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mainActivityActivityScenarioRule = new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void clickTest() {

        onView(withId(R.id.tv_title)).perform(click());
    }
}