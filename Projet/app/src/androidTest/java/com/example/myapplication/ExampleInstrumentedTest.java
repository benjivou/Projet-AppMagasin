package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.myapplication.config.ConfigDAO;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.ListeActivity;
import com.example.myapplication.controller.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    /**
     * Fail to connect:
     * "Login do not exist"
     */

    @SuppressWarnings("unchecked")
    @Test
    public void processBadLoginTestUnmatch(){

        /*
        Write the login
         */
        onView(withId(R.id.login))
                .perform((typeText("NOTRootATall")),closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform((typeText("NOTRootATall")),closeSoftKeyboard());
        /*
        Click on the submit button
         */
        onView(withId(R.id.btnSubmit))
                .perform(click());

        onView(withText(ConfigFront.ERROR_CREATION_PASSWORD_CORRESPONDANCE_PROBLEM))
                .inRoot(new ToastMatcher())
                .check(doesNotExist());
    }

    @Test
    public void processGoodLoginTestMatchMessage(){

        /*
        Write the login
         */
        onView(withId(R.id.login))
                .perform((typeText("NOTRootATall")),closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform((typeText("NOTRootATall")),closeSoftKeyboard());
        /*
        Click on the submit button
         */
        onView(withId(R.id.btnSubmit))
                .perform(click());


        onView(withText(ConfigFront.ERROR_BAD_PAIR_LOGIN_MDP))
                .inRoot(new ToastMatcher())
                .check(matches((isDisplayed())));
    }

    @Test
    public void procesAdmin() {
        /*
        Write the login
         */
        onView(withId(R.id.login))
                .perform((typeText("root")),closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform((typeText(ConfigFront.DEFAULT_PASSWORD)),closeSoftKeyboard());
        /*
        Click on the submit button
         */
        onView(withId(R.id.btnSubmit))
                .perform(click());

        /*
        Check the result of the new activity
         */

        onView(withId(R.id.password2))                      // Check if the password2 field is displayed
                .check(matches(isDisplayed()));



    }
}
