package com.rperez.flickersearchapp.ui.detail

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

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

//    val decodedUrl = Uri.decode(url)
//    Box {
//        Image(
//            painter = rememberAsyncImagePainter(
//                model = decodedUrl,
//                placeholder = painterResource(R.drawable.placeholder),
//                error = painterResource(R.drawable.error),
//                contentScale = ContentScale.FillBounds
//            ),
//            contentDescription = null,
//            modifier = Modifier
//                .padding(8.dp)
//                .size(400.dp)
//        )
//    }

    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .padding(8.dp)
            .background(Color.White),
    ) {
        Text(
            text = "Item Details",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .padding(4.dp)
                .testTag("item_details")
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

        if (description.isNotEmpty()) {
            AndroidView(
                factory = { context ->
                    WebView(context).apply {
                        webViewClient = object : WebViewClient() {
                            override fun shouldOverrideUrlLoading(
                                view: WebView,
                                url: String
                            ): Boolean {
                                return try {
                                    val uri = Uri.parse(url)
                                    val intent = Intent(Intent.ACTION_VIEW, uri)

                                    // Validate URL scheme
                                    if (uri.scheme == null) {
                                        Toast.makeText(
                                            context,
                                            "Invalid URL",
                                            Toast.LENGTH_SHORT
                                        )
                                            .show()
                                        return true
                                    }

                                    context.startActivity(intent)
                                    true
                                } catch (_: ActivityNotFoundException) {
                                    Toast.makeText(
                                        context,
                                        "No app found to open this link",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    true
                                } catch (e: Exception) {
                                    Toast.makeText(
                                        context,
                                        "An error occurred: ${e.localizedMessage}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    true
                                }
                            }

                            override fun onReceivedError(
                                view: WebView?,
                                request: WebResourceRequest?,
                                error: WebResourceError?
                            ) {
                                super.onReceivedError(view, request, error)
                                Toast.makeText(
                                    context,
                                    "Error loading page: ${error?.description ?: "Invalid Link"}",
                                    Toast.LENGTH_SHORT
                                ).show()
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
}