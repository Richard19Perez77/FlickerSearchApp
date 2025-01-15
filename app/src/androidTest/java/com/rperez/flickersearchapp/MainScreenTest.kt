package com.rperez.flickersearchapp

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.rperez.flickersearchapp.ui.main.MainScreen
import com.rperez.flickersearchapp.ui.main.MainViewModel
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class MainScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testSearchFieldDisplaysPlaceholderText() {
        // Arrange: Set the content of the test environment with MainScreen
        composeTestRule.setContent {
            MainScreen(navController = mockNavController(), viewModel = mockMainViewModel())
        }

        // Act & Assert: Find the node with the placeholder text and assert it's displayed
        composeTestRule
            .onNodeWithText("Search Flickr...") // Locate the TextField with placeholder text
            .assertIsDisplayed() // Assert that it is visible in the UI
    }

    private fun mockNavController(): NavHostController {
        return TestNavHostController(ApplicationProvider.getApplicationContext())
    }

    private fun mockMainViewModel() = MainViewModel(
        repository = mockk() // Mock repository as needed
    )
}
