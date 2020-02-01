package com.example.myapplication;

import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.example.myapplication.config.ConfigDAO;
import com.example.myapplication.config.ConfigFront;
import com.example.myapplication.controller.LoginActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onData;
/**
 * Created by Benjamin Vouillon on 31,January,2020
 */
@RunWith(AndroidJUnit4.class)
public class TestListActivityRoot {
    private static final String TAG = "TestListActivityRoot";
    @Rule
    public ActivityTestRule<LoginActivity> activityActivityTestRule = new ActivityTestRule<LoginActivity>(LoginActivity.class);

    @Before
    public void prepareDB(){


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
        onView(withId(R.id.password))
                .perform((typeText("Tutu1234")),closeSoftKeyboard());
        onView(withId(R.id.password2))
                .perform((typeText("Tutu1234")),closeSoftKeyboard());

        onView(withId(R.id.btnSubmit))
                .perform(click());

        /*
        let's start tests
         */
    }
/*    *//*
    Check if we have access to the database
     *//*
    @Test
    public void checkListActivityAccess() {

        onView(withId(R.id.btModeProduct))
                .check(matches(isDisplayed()));

    }*/

    @Test
    public void addProductValid (){
        createProduct();
        /*
        Open the product popup
         */
        onView(withText("tee-shirt"))
        .perform(click());

        onView(withId(R.id.txtName))
                .check(matches(withText("tee-shirt")));
    }

    @Test
    public void addProductFail (){
        createAisle();

        /*
        Click the button
         */
        onView(withId(R.id.btPlus))
                .perform(click());

        /*
        Fill the form
         */
        onView(withId(R.id.txtName))
                .perform(typeText("tee-shirt"),closeSoftKeyboard());

        onView(withId(R.id.txtQuantity))
                .perform(typeText("-1"),closeSoftKeyboard());
        onView(withId(R.id.txtPrice))
                .perform(typeText("45.25"),closeSoftKeyboard());

        onView((withId(R.id.btnSubmit)))
                .perform(click());

        onView(withText(ConfigFront.ERROR_PRODUCTFIELD_QUANTITY))
                .inRoot(new ToastMatcher())
                .check(matches((isDisplayed())));

    }

    @Test
    public void supressProductSucess(){
        createProduct();
        /*
        Check if the product was created
         */
        onView((withText("tee-shirt")))
                .check(matches(isDisplayed()));
         /*
        Open the product popup
         */
        onView(withText("tee-shirt"))
                .perform(click());

        /*
        Flush the object
         */
        onView(withId(R.id.btnDelete))
                .perform(click());

        /*
        Check if the product is removed
         */
        onView(withText("tee-shirt"))
                .check(doesNotExist());

    }

    public void createProduct(){

        createAisle();

        /*
        Click the button
         */
        onView(withId(R.id.btPlus))
                .perform(click());

        /*
        Fill the form
         */
        onView(withId(R.id.txtName))
                .perform(typeText("tee-shirt"),closeSoftKeyboard());

        onView(withId(R.id.txtQuantity))
                .perform(typeText("457"),closeSoftKeyboard());
        onView(withId(R.id.txtPrice))
                .perform(typeText("45.25"),closeSoftKeyboard());

        onView((withId(R.id.btnSubmit)))
                .perform(click());

    }

    public void createAisle(){
           /*
       Create an aisle
         */

        onView(withId(R.id.btShoppingBasket))
                .perform(click());

        onView(withId(R.id.txtNomRayon))
                .perform(typeText("Enfant"));

        onView((withId(R.id.btnSubmit)))
                .perform(click());

    }
}
