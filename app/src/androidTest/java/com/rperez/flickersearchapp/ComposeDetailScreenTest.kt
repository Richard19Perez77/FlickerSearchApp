package com.rperez.flickersearchapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rperez.flickersearchapp.ui.detail.DetailScreen
import com.rperez.flickersearchapp.ui.theme.FlickerSearchAppTheme
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ComposeDetailScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun defaultTextTest() {
        composeTestRule.setContent {
            FlickerSearchAppTheme {
                DetailScreen(
                    "",
                    "",
                    "",
                    "",
                    "",
                )
            }
        }

        composeTestRule.onNodeWithText("Item Details").assertIsDisplayed()
    }
}