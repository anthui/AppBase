package com.ant.modul.test.androidTest;

import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.ant.anttestlibrary.R;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.intent.Intents.getIntents;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.ComponentNameMatchers.hasShortClassName;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.toPackage;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/3.
 * describe：
 */

@RunWith(JUnit4.class)
@LargeTest
public class SecondActivityTest {

    @Rule
    public IntentsTestRule mIntentTestRule = new IntentsTestRule<>(LoginActivity.class);


    @Before
    public void setUp() {


        Instrumentation.ActivityResult activityResult = new Instrumentation.ActivityResult(Activity.RESULT_OK, null);

//        intending(allOf(toPackage(InstrumentationRegistry.getInstru)))
//        intending(allOf(
//                toPackage(InstrumentationRegistry.getInstrumentation().getComponentName().getPackageName()),
//                hasComponent(hasShortClassName("SecondActivity"))
//        )).respondWith(activityResult);


    }



    @Test
    public void testJump() throws Exception {
        onView(withId(R.id.btn_login)).perform(ViewActions.click());
        //目标Intent是否启动了
        String packageName = InstrumentationRegistry.getInstrumentation().getContext().getPackageName();

        Assert.assertEquals(packageName, "com.ant.antTest.test");
        System.out.println(packageName);

        intended(hasComponent(SecondActivity.class.getName()));

        for (int i = 0; i < getIntents().size(); i++) {

        }
        intended(allOf(
                toPackage("com.ant.antTest"),
                hasComponent(SecondActivity.class.getName()),
                hasExtra("id", "123")
        ));


    }
}