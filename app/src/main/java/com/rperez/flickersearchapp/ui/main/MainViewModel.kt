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

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: FlickrRepository) : ViewModel() {
    private val _state = mutableStateOf(MainState())
    val state: State<MainState> get() = _state

    fun search(query: String) {
        _state.value = MainState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.searchPhotos(query)
            _state.value = MainState(images = result)
        }
    }
}
