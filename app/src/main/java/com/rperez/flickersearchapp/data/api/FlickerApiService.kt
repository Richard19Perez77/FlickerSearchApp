package com.rperez.flickersearchapp.data.api

import com.rperez.flickersearchapp.data.model.FlickrResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Service interface for interacting with the Flickr Public API.
 *
 * This interface defines the endpoints used to fetch data from Flickr's public feed.
 * Retrofit is used to make HTTP requests, and the responses are deserialized into data models.
 */
interface FlickrApiService {

    /**
     * Fetches photos from the Flickr public feed based on the provided tags.
     *
     * @param tags A comma-separated list of tags to filter the photos.
     * @return A [FlickrResponse] containing a list of photos matching the tags.
     *
     * Example URL: `https://api.flickr.com/services/feeds/photos_public.gne?format=json&nojsoncallback=1&tags=tag1,tag2`
     */
    @GET("services/feeds/photos_public.gne?format=json&nojsoncallback=1")
    suspend fun searchPhotos(@Query("tags") tags: String): FlickrResponse
}
