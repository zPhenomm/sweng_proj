package com.example.software_engineering_project

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented tests for the app, which will execute on an Android device.
 */
@RunWith(AndroidJUnit4::class)
class TEDTesting {
    @Test
    fun testCalculator() {
        // test Calculator, sqrt(4) = 2 and sqrt(4 = Syntax Error
        var activityScenario = launch(CalcActivity::class.java)
        onView(withId(R.id.calcBtnRoot)).perform(ViewActions.click())
        onView(withId(R.id.calcBtn4)).perform(ViewActions.click())
        onView(withId(R.id.calcBtnCloseBracket)).perform(ViewActions.click())
        onView(withId(R.id.calcBtnEq)).perform(ViewActions.click())
        onView(withId(R.id.eqView)).check(matches(withText("2")))
        activityScenario.close()
        Thread.sleep(100)  // wait a bit for animations to conclude
        activityScenario = launch(CalcActivity::class.java)
        onView(withId(R.id.calcBtnRoot)).perform(ViewActions.click())
        onView(withId(R.id.calcBtn4)).perform(ViewActions.click())
        onView(withId(R.id.calcBtnEq)).perform(ViewActions.click())
        onView(withId(R.id.eqView)).check(matches(withText("Syntax Error")))
        activityScenario.close()
    }
    @Test
    fun testWeather() {
        // test if inputting invalid location leads to correct feedback
        val activityScenario = launch(WeatherActivity::class.java)
        onView(withId(R.id.editLocation)).perform(typeText("nevergonnagiveyouupnevergonnaletyoudown"))
        onView(withId(R.id.btnGo)).perform(ViewActions.click())
        Thread.sleep(500)  // wait for API response
        onView(withId(R.id.weatherText)).check(matches(withText("Invalid location")))
        activityScenario.close()
    }
}