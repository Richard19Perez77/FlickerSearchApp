package com.rperez.flickersearchapp.data.api

import com.rperez.flickersearchapp.data.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApiService {
    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchPhotos(@Query("tags") tags: String): FlickrResponse
}
