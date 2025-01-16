package com.rperez.flickersearchapp.ui.main

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.rperez.flickersearchapp.state.MainState

/**
 * Displays the main screen of the application, including a search bar, a loading indicator, and
 * a list of image results fetched from the Flickr API.
 *
 * @param navController The [NavHostController] for navigating between screens.
 * @param state The [MainState] the UI state and data fetching.
 * @param search The [MainViewModel.search] the update method on text entered.
 */
@Composable
fun MainScreen(
    navController: NavHostController,
    state: State<MainState>,
    search: (String) -> Unit
) {

    // Layout for the main screen.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {

        // Search bar for entering a query to fetch images.
        var query by remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = query,
            onValueChange = {
                query = it
                search(it.text) // Trigger search in ViewModel when query changes.
            },
            modifier = Modifier.fillMaxWidth().testTag("search_flickr"),
            placeholder = { Text("Search Flickr...") }
        )

        // Show a loading indicator while data is being fetched.
        if (state.value.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("loading"),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp))
            }
        } else {
            // Display the list of images in a lazy column.
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.value.images.count()) { i ->
                    val item = state.value.images[i]
                    val route = "detail/${Uri.encode(item.title)}/${Uri.encode(item.description)}/${
                        Uri.encode(item.author)
                    }/${Uri.encode(item.published)}/${Uri.encode(item.media.m)}"
                    Row(
                        modifier = Modifier
                            .testTag("item$i")
                            .clickable {
                                navController.navigate(route)
                            }
                    ) {
                        // Display the image thumbnail.
                        Image(
                            painter = rememberAsyncImagePainter(item.media.m),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .padding(8.dp)
                        )
                        // Display the title of the image.
                        Text(item.title, modifier = Modifier.padding(8.dp))
                    }
                }
            }
        }
    }
}