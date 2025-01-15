package com.rperez.flickersearchapp.data.repository

import com.rperez.flickersearchapp.data.api.FlickrApiService
import com.rperez.flickersearchapp.data.model.FlickrItem
import jakarta.inject.Inject

class FlickrRepository @Inject constructor(private val api: FlickrApiService) {
    suspend fun searchPhotos(query: String): List<FlickrItem> {
        return api.searchPhotos(query).items
    }
}
