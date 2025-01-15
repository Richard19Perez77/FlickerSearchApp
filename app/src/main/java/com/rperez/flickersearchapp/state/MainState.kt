package com.rperez.flickersearchapp.state

import com.rperez.flickersearchapp.data.model.FlickrItem

data class MainState(
    val isLoading: Boolean = false,
    val images: List<FlickrItem> = emptyList()
)
