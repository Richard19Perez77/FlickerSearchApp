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
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

/**
 * Displays detailed information about a specific photo item.
 *
 * This screen shows the photo, title, description, author, and published date
 * in a vertically scrollable column layout.
 *
 * @param title The title of the photo.
 * @param description The description of the photo.
 * @param author The author of the photo.
 * @param published The publication date of the photo.
 * @param url The URL of the photo to be displayed.
 */
@Composable
fun DetailScreen(
    title: String,
    description: String,
    author: String,
    published: String,
    url: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Item Details",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(4.dp)
        )
        // Displays the photo using the provided URL.
        Image(
            painter = rememberAsyncImagePainter(url),
            contentDescription = null, // No specific description for accessibility
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        // Displays the title of the photo.
        Text(
            text = "Title: $title",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(4.dp)
        )
        // Displays the description of the photo.
        Text(
            text = "Description: $description",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(4.dp)
        )
        // Displays the author of the photo.
        Text(
            text = "Author: $author",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(4.dp)
        )
        // Displays the publication date of the photo.
        Text(
            text = "Published: $published",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(4.dp)
        )
    }
}
