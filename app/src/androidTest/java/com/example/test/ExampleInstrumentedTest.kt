package com.example.test

import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.registerIdlingResources
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.util.Log
import kotlinx.coroutines.Delay
import kotlinx.coroutines.delay


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
//    public var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    public var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.test", appContext.packageName)
    }

    @Test
    fun listGoesOverTheFold() {
        onView(withText("NEXT")).check(matches(isDisplayed()))
        Log.d("test","next OK")
    }

    @Test
    fun bindingText() {
        onView(withId(R.id.editText)).perform(replaceText("test"))
        onView(withId(R.id.mirrorText)).check(matches(withText("test")))
        onView(withId(R.id.buttonNext)).perform(click())
        onView(withText("戻る")).check(matches(isDisplayed()))
    }

//    @Test
//    fun checkMovingView(){
//        onView(withText("戻る")).check(matches(isDisplayed()))
//    }
}
