package com.rperez.flickersearchapp.ui.detail

import android.content.Intent
import android.net.Uri
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter

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
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        Text(
            text = "Item Details",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(4.dp)
                .testTag("item_details")
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

        AndroidView(
            modifier = Modifier.fillMaxWidth(),
            factory = { context ->
                WebView(context).apply {
                    webViewClient = object : WebViewClient() {
                        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            context.startActivity(intent)
                            return true
                        }
                    }
                    loadDataWithBaseURL(null, description, "text/html", "UTF-8", null)
                }
            },
            update = { webView ->
                webView.loadDataWithBaseURL(null, description, "text/html", "UTF-8", null)
            }
        )
    }
}
