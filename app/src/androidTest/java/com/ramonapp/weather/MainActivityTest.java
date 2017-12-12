package com.ramonapp.weather;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.ramonapp.weather.ui.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by vahid on 12/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<MainActivity>(MainActivity.class,false,false);

    @Test
    public void startSearchActivity(){
        launchMainActivity();
        onView(withId(R.id.search_btn)).perform(click());
        onView(withId(R.id.search_vw)).check(matches(isDisplayed()));
    }

    private void launchMainActivity(){
        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation()
                .getTargetContext(),MainActivity.class);
        activityTestRule.launchActivity(intent);
    }
}
