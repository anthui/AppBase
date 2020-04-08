package com.ant.modul.test.javaTest;

import android.app.Application;
import android.content.Intent;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;

import com.ant.anttestlibrary.R;
import com.ant.modul.test.androidTest.LoginActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.robolectric.Shadows.shadowOf;

/**
 * copyright：
 * author：anthui
 * Version：1.0
 * creation date： 2020/4/3.
 * describe：
 */
@RunWith(RobolectricTestRunner.class)
public class MyActivityTest {

    @Test
    public void clickingButton_shouldChangeMessage() {

//        ActivityScenario<LoginActivity> launch = ActivityScenario.launch(LoginActivity.class);
//        launch.onActivity(new ActivityScenario.ActivityAction<LoginActivity>() {
//            @Override
//            public void perform(LoginActivity activity) {
//
//                activity.startActivity();
//
//            }
//        });
//
//        LoginActivity activity = Robolectric.setupActivity(LoginActivity.class);
////        activity.findViewById(R.id.login).performClick();
//        Intent expectedIntent = new Intent(activity, LoginActivity.class);
//        Intent actual = shadowOf((Application) ApplicationProvider.getApplicationContext()).getNextStartedActivity();
//        assertEquals(expectedIntent.getComponent(), actual.getComponent());

    }
}
