package com.rperez.flickersearchapp

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import com.rperez.flickersearchapp.state.MainState
import com.rperez.flickersearchapp.ui.main.MainScreen
import com.rperez.flickersearchapp.ui.main.MainViewModel
import com.rperez.flickersearchapp.ui.theme.FlickerSearchAppTheme
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class ComposeMainScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @MockK
    lateinit var navController: NavHostController

    @MockK(relaxed = true)
    lateinit var viewModel: MainViewModel

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    @Test
    fun defaultTextTest() {
        val mockState = mutableStateOf(MainState())
        every { viewModel.state } answers { mockState }

        composeTestRule.setContent {
            FlickerSearchAppTheme {
                MainScreen(
                    navController,
                    viewModel
                )
            }
        }

        composeTestRule.onNodeWithText("Search Flickr...").assertIsDisplayed()
    }

    @Test
    fun loadingStateTest() {
        val mockState = mutableStateOf(MainState(isLoading = true))

        every { viewModel.state } returns mockState

        composeTestRule.setContent {
            FlickerSearchAppTheme {
                MainScreen(
                    navController,
                    viewModel
                )
            }
        }

        composeTestRule.onNodeWithText("Loading...").assertIsDisplayed()
    }

}
