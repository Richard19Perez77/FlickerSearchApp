package com.rperez.flickersearchapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class MainScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun before(){
        MockKAnnotations.init(this)
    }

    @Test
    fun testSearchFieldDisplaysPlaceholderText() {
        composeTestRule
            .onNodeWithText("Search Flickr...")
            .assertIsDisplayed()
    }
}
