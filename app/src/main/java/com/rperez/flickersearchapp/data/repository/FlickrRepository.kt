package com.rperez.flickersearchapp.data.repository

import com.rperez.flickersearchapp.data.api.FlickrApiService
import com.rperez.flickersearchapp.data.model.FlickrItem
import jakarta.inject.Inject

/**
 * Repository class for handling data operations related to Flickr's public API.
 *
 * This class abstracts the data source (e.g., network) and provides a clean API for fetching
 * photos. It interacts with the [FlickrApiService] to retrieve data from Flickr's public feed.
 *
 * @property api The [FlickrApiService] instance for making API calls.
 */
class FlickrRepository @Inject constructor(
    private val api: FlickrApiService
) {

    /**
     * Fetches a list of photos from the Flickr public feed based on the provided search query.
     *
     * @param query A string containing comma-separated tags to filter the photos.
     * @return A list of [FlickrItem] objects representing the photos retrieved from the API.
     *
     * Example usage:
     * ```
     * val photos = repository.searchPhotos("nature,forest")
     * ```
     */
    suspend fun searchPhotos(query: String): List<FlickrItem> {
        return api.searchPhotos(query).items
    }
}
