package com.example.androidtest;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestSystem {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTestSystemNormalBehavior() {
        ViewInteraction i1 = onView(withId(R.id.input1));
        ViewInteraction i2 = onView(withId(R.id.input2));
        ViewInteraction button = onView(withId(R.id.button));
        ViewInteraction out = onView(withId(R.id.out));

        i1.perform(replaceText("abcd"), closeSoftKeyboard());
        i2.perform(replaceText("2"), closeSoftKeyboard());
        button.perform(click());

        out.check(matches(withText("The suffix is:cd")));
    }
    @Test
    public void mainActivityTestSystemNonNumberSecondInput() {
        ViewInteraction i1 = onView(withId(R.id.input1));
        ViewInteraction i2 = onView(withId(R.id.input2));
        ViewInteraction button = onView(withId(R.id.button));
        ViewInteraction out = onView(withId(R.id.out));

        i1.perform(replaceText("abcd"), closeSoftKeyboard());
        i2.perform(replaceText("qw"), closeSoftKeyboard());
        button.perform(click());

        out.check(matches(withText("The second input should be an integer")));
    }

    @Test
    public void mainActivityTestSystemSecondInputGreaterThanLengthOfFirstParameter() {
        ViewInteraction i1 = onView(withId(R.id.input1));
        ViewInteraction i2 = onView(withId(R.id.input2));
        ViewInteraction button = onView(withId(R.id.button));
        ViewInteraction out = onView(withId(R.id.out));

        i1.perform(replaceText("abcd"), closeSoftKeyboard());
        i2.perform(replaceText("10"), closeSoftKeyboard());
        button.perform(click());

        out.check(matches(withText("You cannot give a number that exceeds the length of the input one!")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
