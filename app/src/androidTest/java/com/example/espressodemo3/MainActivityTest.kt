package com.example.espressodemo3

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun whenScrollTheRecyclerViewThenItemWithTextShouldExist() {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText("$ITEM_IN_LIST$PARTICULAR_POSITION"))
            )
        )
    }

    @Test
    fun whenScrollTheRecyclerViewToParticularPositionCheckItsText() {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                PARTICULAR_POSITION, ViewActions.click()
            )
        )

        val itemAtElementText = "$ITEM_IN_LIST$PARTICULAR_POSITION"
        onView(withText(itemAtElementText)).check(matches(isDisplayed()))
    }

    @Test
    fun whenScrollTheRecyclerViewToParticularPositionPosition() {
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                PARTICULAR_POSITION, ViewActions.click()
            )
        )
    }

    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains the special text.
        onView(withId(R.id.recyclerView))
            .perform(
                // scrollTo will fail the test if no item matches.
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(NOT_IN_LIST))
                )
            )
    }

    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        // Scroll to the middle element in the RecyclerView.
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(MIDDLE_POSITION))

        val specialText =
            InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(R.string.middle)
        onView(withText(specialText)).check(matches(isDisplayed()))
    }

    private companion object {
        const val NOT_IN_LIST = "not in the list"
        const val ITEM_IN_LIST = "This is element #"
        const val PARTICULAR_POSITION = 300
        const val MIDDLE_POSITION = 250
    }
}