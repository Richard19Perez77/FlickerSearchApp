package com.rperez.flickersearchapp.ui.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rperez.flickersearchapp.data.repository.FlickrRepository
import com.rperez.flickersearchapp.state.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the state and business logic of the main screen.
 *
 * This ViewModel interacts with the [FlickrRepository] to fetch photos from the Flickr API
 * and updates the UI state represented by [MainState].
 *
 * @property repository The [FlickrRepository] used for fetching photo data.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: FlickrRepository
) : ViewModel() {

    // Backing field for the UI state.
    private val _state = mutableStateOf<MainState>(MainState.Default())

    /**
     * Publicly exposed immutable state representing the UI.
     */
    val state: State<MainState> get() = _state

    /**
     * Fetches photos from the Flickr API based on the provided search query.
     *
     * Updates the UI state to show a loading indicator while fetching data, and
     * populates the state with the resulting list of photos once the operation completes.
     *
     * @param query The search query used to filter photos by tags.
     */
    fun search(query: String) {
        // Update the state to indicate loading.
        _state.value = MainState.Loading

        // Perform the API call in a background thread.
        viewModelScope.launch(Dispatchers.IO) {
            _state.value = try {
                val result = repository.searchPhotos(query)
                MainState.Success(items = result)
            } catch (_: Exception) {
                MainState.Error()
            }
        }
    }
}
