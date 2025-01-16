package com.rperez.flickersearchapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScreenToDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testSearchFieldChangeDisplaysNewText() {
        composeTestRule
            .onNodeWithTag("search_flickr").performTextInput("a")

        composeTestRule
            .onNodeWithText("a")
            .assertIsDisplayed()
    }

    @Test
    fun testSearchFieldDisplaysPlaceholderText() {
        composeTestRule
            .onNodeWithTag("search_flickr").performTextInput("a")

        composeTestRule
            .onNodeWithText("a")
            .assertIsDisplayed()
    }

    @Test
    fun testSearchFieldChangeHasResults() {
        composeTestRule
            .onNodeWithTag("search_flickr").performTextInput("a")

        Thread.sleep(3000)

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("item0").assertIsDisplayed()
    }

    @Test
    fun testSearchFieldChangeClickItem1ToDetails() {
        composeTestRule
            .onNodeWithTag("search_flickr").performTextInput("a")

        Thread.sleep(3000)

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("item0").performClick()

        Thread.sleep(3000)

        composeTestRule.waitForIdle()

        composeTestRule
            .onNodeWithTag("item_details").assertIsDisplayed()
    }
}
