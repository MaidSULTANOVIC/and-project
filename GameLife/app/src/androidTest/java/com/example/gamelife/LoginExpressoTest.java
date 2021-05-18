package com.example.gamelife;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * UI Test to test error message and login components
 *
 * NB : If you have low internet speed, the tests can fail because the async function didn't finish at time.
 */
@RunWith(JUnit4.class)
public class LoginExpressoTest {


    private String email;

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule
            = new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void initValidString() {
        //email for test
        email = "maid@maid.com";
    }

    @Test
    public void changeText_Username() {
        // Type text
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText(email), closeSoftKeyboard());

        // Check that the text was changed.
        onView(withId(R.id.editTextTextPersonName))
                .check(matches(withText(email)));
    }

    @Test
    public void checkErrorMessage(){
        onView(withId(R.id.buttonLogin)).perform(click());

        onView(withId(R.id.txtError))
                .check(matches(withText("Username/Password error")));

    }

    @Test
    public void checkEmailFormatError(){
        // Type username address
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("notEmailFormat"), closeSoftKeyboard());

        // Type password
        onView(withId(R.id.editTextTextPassword))
                .perform(typeText("randomPassword"), closeSoftKeyboard());

        //Click on login button
        onView(withId(R.id.buttonLogin)).perform(click());

        //Because it is an async function that is called on click, we wait 2 seconds for the function to finish
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Check error message
        onView(withId(R.id.txtError))
                .check(matches(withText("The email address is badly formatted.")));

    }

    @Test
    public void checkWrongEmailError(){
        // Type username adress
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("maid@maidddd.com"), closeSoftKeyboard());

        // Type password
        onView(withId(R.id.editTextTextPassword))
                .perform(typeText("randomPassword"), closeSoftKeyboard());

        //Click on login button
        onView(withId(R.id.buttonLogin)).perform(click());

        //Because it is an async function that is called on click, we wait 2 seconds for the function to finish
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Check error message
        onView(withId(R.id.txtError))
                .check(matches(withText("There is no user record corresponding to this identifier. The user may have been deleted.")));
    }

    @Test
    public void checkExistingAccountError(){
        // Type username adress
        onView(withId(R.id.editTextTextPersonName))
                .perform(typeText("maid@maid.com"), closeSoftKeyboard());

        // Type password
        onView(withId(R.id.editTextTextPassword))
                .perform(typeText("randomPassword90"), closeSoftKeyboard());

        //Click on login button
        onView(withId(R.id.buttonRegister)).perform(click());

        //Because it is an async function that is called on click, we wait 2 seconds for the function to finish
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Check error message
        onView(withId(R.id.txtError))
                .check(matches(withText("The email address is already in use by another account.")));
    }
}
