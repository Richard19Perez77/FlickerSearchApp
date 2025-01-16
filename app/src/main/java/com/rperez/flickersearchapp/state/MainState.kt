package com.rperez.flickersearchapp.state

import com.rperez.flickersearchapp.data.model.FlickrItem

sealed class MainState {
    data class Default(val message: String = "Start entering text for results"): MainState()
    object Loading : MainState()
    data class Success(val items: List<FlickrItem> = emptyList<FlickrItem>()) : MainState()
    data class Error(val message: String = "Error Loading") : MainState()
}
