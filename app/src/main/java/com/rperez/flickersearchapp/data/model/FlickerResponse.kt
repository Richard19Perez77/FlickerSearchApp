package com.rperez.flickersearchapp.data.model

data class FlickrResponse(val items: List<FlickrItem>)

data class FlickrItem(
    val title: String,
    val media: Media,
    val description: String,
    val author: String,
    val published: String
)

data class Media(val m: String)
