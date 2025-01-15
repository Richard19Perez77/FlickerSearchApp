package com.rperez.flickersearchapp.ui.main

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter

/**
 * Displays the main screen of the application, including a search bar, a loading indicator, and
 * a list of image results fetched from the Flickr API.
 *
 * @param navController The [NavHostController] for navigating between screens.
 * @param viewModel The [MainViewModel] for managing the UI state and data fetching.
 */
@Composable
fun MainScreen(
    navController: NavHostController,
    viewModel: MainViewModel = hiltViewModel()
) {
    // Observe the UI state from the ViewModel.
    val state = viewModel.state

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
                viewModel.search(it.text) // Trigger search in ViewModel when query changes.
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search Flickr...") }
        )

        // Show a loading indicator while data is being fetched.
        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        // Display the list of images in a lazy column.
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.value.images.count()) { i ->
                val item = state.value.images[i]
                val route = "detail/${Uri.encode(item.title)}/${Uri.encode(item.description)}/${Uri.encode(item.author)}/${Uri.encode(item.published)}/${Uri.encode(item.media.m)}"
                Row(
                    modifier = Modifier.clickable {
                        navController.navigate(route)
                    }
                ) {
                    // Display the image thumbnail.
                    Image(
                        painter = rememberImagePainter(item.media.m),
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
