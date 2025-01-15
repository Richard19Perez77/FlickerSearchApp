package com.rperez.flickersearchapp.state

import com.rperez.flickersearchapp.data.model.FlickrItem

/**
 * Represents the UI state for the main screen of the application.
 *
 * This state is used to track the loading status and the list of photos fetched from the Flickr API.
 *
 * @property isLoading Indicates whether a data-fetching operation is in progress.
 * @property images A list of [FlickrItem] objects representing the photos retrieved from the API.
 */
data class MainState(
    val isLoading: Boolean = false,
    val images: List<FlickrItem> = emptyList()
)
