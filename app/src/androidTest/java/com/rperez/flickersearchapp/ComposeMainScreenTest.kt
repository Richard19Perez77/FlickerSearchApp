package com.rperez.flickersearchapp

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.MutableLiveData
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

    @MockK
    lateinit var viewModel: MainViewModel

    private val mockState = mutableStateOf<MainState>(MainState())

    @Before
    fun before(){
        MockKAnnotations.init(this)
        every { viewModel.state } returns mockState
    }

    @Test
    fun defaultTextTest() {
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
}
