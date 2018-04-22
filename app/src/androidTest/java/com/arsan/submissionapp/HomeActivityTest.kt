package com.arsan.submissionapp

import android.app.PendingIntent.getActivity
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenu
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.internal.NavigationMenuItemView
import android.support.design.widget.BottomNavigationView
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import com.arsan.submissionapp.R.id.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.support.v4.content.ContextCompat
import android.graphics.drawable.Drawable
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.ViewInteraction
import com.arsan.submissionapp.R.layout.fragment_next_match
import org.hamcrest.CoreMatchers.endsWith


@RunWith(AndroidJUnit4::class)
class HomeActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(HomeActivity::class.java)

    @Test
    fun testAppAllBehaviour(){
        // Testing on Prev Match Navigation
        onView(withId(navigation)).check(matches(isDisplayed()))
        onView(withId(navigation_prevmatch)).perform(click())
        onView(withId(R.id.fragment_prev_match)).check(matches(isDisplayed()))
        onView(withId(progress_bar_prev)).perform(ProgressBar.VISIBLE)
        onView(withId(progress_bar_prev)).perform(ProgressBar.INVISIBLE)
        onView(withId(rv_schedule_prev)).check(matches(isDisplayed()))
        onView(withId(swipe_refresh_prev)).perform(swipeDown())
        onView(withId(rv_schedule_prev)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(rv_schedule_prev)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
        onView(withId(match_detail)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()

        // Testing on Next Match Navigation
        onView(withId(navigation_nextmatch)).perform(click())
        onView(withId(R.id.fragment_next_match)).check(matches(isDisplayed()))
        onView(withId(progress_bar_next)).perform(ProgressBar.VISIBLE)
        onView(withId(progress_bar_next)).perform(ProgressBar.INVISIBLE)
        onView(withId(rv_schedule_next)).check(matches(isDisplayed()))
        onView(withId(swipe_refresh_next)).perform(swipeDown())
        onView(withId(rv_schedule_next)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        onView(withId(rv_schedule_next)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(7, click()))
        onView(withId(match_detail)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()

        // Testing on Favorites Match Navigation
        onView(withId(navigation_favorites)).perform(click())
        onView(withId(R.id.fragment_favorites_match)).check(matches(isDisplayed()))
        onView(withId(rv_schedule_favorites)).check(matches(isDisplayed()))
        onView(withId(swipe_refresh_favorites)).perform(swipeDown())
        onView(withId(rv_schedule_favorites)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rv_schedule_favorites)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(match_detail)).check(matches(isDisplayed()))

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        pressBack()

    }
}

private fun ViewInteraction.perform(visible: Int) {}
