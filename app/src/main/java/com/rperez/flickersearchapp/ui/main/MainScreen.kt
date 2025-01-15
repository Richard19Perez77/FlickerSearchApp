package com.rperez.flickersearchapp.ui.main

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

@Composable
fun MainScreen(navController: NavHostController, viewModel: MainViewModel = hiltViewModel()) {
    val state = viewModel.state
    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        var query by remember { mutableStateOf(TextFieldValue()) }
        TextField(
            value = query,
            onValueChange = {
                query = it
                viewModel.search(it.text)
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text("Search Flickr...") }
        )

        if (state.value.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.value.images.count()) { i ->
                var item = state.value.images[i]
                Row(modifier = Modifier.clickable {
                    navController.navigate(
                        "detail/${item.title}/${item.description}/${item.author}/${item.published}/${item.media.m}"
                    )
                }) {
                    Image(
                        painter = rememberImagePainter(item.media.m),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp).padding(8.dp)
                    )
                    Text(item.title, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

