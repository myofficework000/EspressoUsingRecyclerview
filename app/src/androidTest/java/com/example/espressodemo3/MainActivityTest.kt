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

/**
 * Espresso test class for testing RecyclerView interactions in MainActivity.
 */
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    /**
     * Test to verify that an item with specific text exists in the RecyclerView after scrolling.
     */
    @Test
    fun whenScrollTheRecyclerViewThenItemWithTextShouldExist() {
        // Perform scrolling to find the item with specific text.
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                hasDescendant(withText("$ITEM_IN_LIST$PARTICULAR_POSITION"))
            )
        )
    }

    /**
     * Test to verify that clicking on an item at a particular position displays the expected text.
     */
    @Test
    fun whenScrollTheRecyclerViewToParticularPositionCheckItsText() {
        // Click on an item at a particular position.
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                PARTICULAR_POSITION, ViewActions.click()
            )
        )

        // Verify that the clicked item displays the expected text.
        val itemAtElementText = "$ITEM_IN_LIST$PARTICULAR_POSITION"
        onView(withText(itemAtElementText)).check(matches(isDisplayed()))
    }

    /**
     * Test to simply click on an item at a particular position without verifying the displayed text.
     */
    @Test
    fun whenScrollTheRecyclerViewToParticularPositionPosition() {
        // Click on an item at a particular position without verifying the displayed text.
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                PARTICULAR_POSITION, ViewActions.click()
            )
        )
    }

    /**
     * Test to verify that attempting to scroll to an item that does not exist throws a PerformException.
     */
    @Test(expected = PerformException::class)
    fun itemWithText_doesNotExist() {
        // Attempt to scroll to an item that contains specific text that is not in the list.
        onView(withId(R.id.recyclerView))
            .perform(
                RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                    hasDescendant(withText(NOT_IN_LIST))
                )
            )
    }

    /**
     * Test to verify that the middle item in the RecyclerView displays the special text.
     */
    @Test
    fun itemInMiddleOfList_hasSpecialText() {
        // Scroll to the middle element in the RecyclerView.
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(MIDDLE_POSITION))

        // Verify that the middle item displays the special text.
        val specialText =
            InstrumentationRegistry.getInstrumentation().targetContext.resources.getString(R.string.middle)
        onView(withText(specialText)).check(matches(isDisplayed()))
    }

    // Constants declaration

    private companion object {
        // Constants for test data
        const val NOT_IN_LIST = "not in the list"
        const val ITEM_IN_LIST = "This is element #"
        const val PARTICULAR_POSITION = 300
        const val MIDDLE_POSITION = 250
    }
}
