package com.rperez.flickersearchapp.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

@Composable
fun DetailScreen(title: String, description: String, author: String, published: String, url: String) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Image(
            painter = rememberImagePainter(url),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Text("Title: $title", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(4.dp))
        Text("Description: $description", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(4.dp))
        Text("Author: $author", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(4.dp))
        Text("Published: $published", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(4.dp))
    }
}
