package com.rperez.flickersearchapp.data.model

/**
 * Represents the response from the Flickr public feed API.
 *
 * @property items A list of [FlickrItem] objects representing individual photo entries.
 */
data class FlickrResponse(
    val items: List<FlickrItem>
)

/**
 * Represents an individual photo item in the Flickr public feed.
 *
 * @property title The title of the photo.
 * @property media A [Media] object containing the URL of the photo.
 * @property description A description of the photo in HTML format.
 * @property author The author of the photo.
 * @property published The publication date of the photo in ISO 8601 format.
 */
data class FlickrItem(
    val title: String,
    val media: Media,
    val description: String,
    val author: String,
    val published: String
)

/**
 * Represents the media object of a Flickr photo.
 *
 * @property m The URL of the photo.
 */
data class Media(
    val m: String
)
