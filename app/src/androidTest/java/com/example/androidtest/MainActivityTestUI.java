package com.example.androidtest;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.androidtest.tools.Suffix;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTestUI {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTestUINormalBehavior() {
        ViewInteraction i1 = onView(withId(R.id.input1));
        ViewInteraction i2 = onView(withId(R.id.input2));
        ViewInteraction button = onView(withId(R.id.button));
        ViewInteraction out = onView(withId(R.id.out));

        String s="abcd";
        Integer n=2;

        i1.perform(replaceText(s), closeSoftKeyboard());
        i2.perform(replaceText(n.toString()), closeSoftKeyboard());
        button.perform(click());

        Suffix s1=new Suffix(s,n);

        try {
            out.check(matches(withText("The suffix is:" + s1.getSuffix())));
        }
        catch (Exception e){
            out.check(matches(withText(e.getMessage())));
        }
    }
    @Test
    public void mainActivityTestUISecondInputNotInteger() {
        ViewInteraction i1 = onView(withId(R.id.input1));
        ViewInteraction i2 = onView(withId(R.id.input2));
        ViewInteraction button = onView(withId(R.id.button));
        ViewInteraction out = onView(withId(R.id.out));

        String s1="abcd";
        String s2="qw";

        i1.perform(replaceText(s1), closeSoftKeyboard());
        i2.perform(replaceText(s2), closeSoftKeyboard());
        button.perform(click());

        try {
            out.check(matches(withText("The second input should be an integer")));
        }
        catch (Exception e){
            out.check(matches(withText(e.getMessage())));
        }
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
