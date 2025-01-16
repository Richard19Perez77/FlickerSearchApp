package com.rperez.flickersearchapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.NavHostController
import com.rperez.flickersearchapp.state.MainState
import com.rperez.flickersearchapp.ui.main.MainScreen
import com.rperez.flickersearchapp.ui.theme.FlickerSearchAppTheme
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
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

    private lateinit var mainState: MutableState<MainState>

    @Before
    fun before() {
        MockKAnnotations.init(this)
        mainState = mutableStateOf(MainState())
    }

    @Test
    fun defaultTextTest() {
        composeTestRule.setContent {
            FlickerSearchAppTheme {
                MainScreen(
                    navController,
                    mainState
                ) {}
            }
        }

        composeTestRule.onNodeWithTag("search_flickr").assertIsDisplayed()
    }

    @Test
    fun loadingStateTest() {
        mainState.value = MainState(
            isLoading = true,
        )

        composeTestRule.setContent {
            FlickerSearchAppTheme {
                MainScreen(
                    navController,
                    mainState
                ) {}
            }
        }

        composeTestRule.onNodeWithTag("loading").assertIsDisplayed()
    }
}
