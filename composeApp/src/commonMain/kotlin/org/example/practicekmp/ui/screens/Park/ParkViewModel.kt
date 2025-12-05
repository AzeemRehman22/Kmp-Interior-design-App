package org.example.practicekmp.ui.screens.Washroom


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.practicekmp.data.ApiResponse
import org.example.practicekmp.data.Photo
import org.example.practicekmp.network.PostRepository

class ParkViewModel : ViewModel() {

    private val repository = PostRepository()
    private val _photosState = MutableStateFlow<ApiResponse<List<Photo>>>(ApiResponse.Loading())
    val photosState: StateFlow<ApiResponse<List<Photo>>> = _photosState.asStateFlow()

    init {
        loadSearchPhotos()
    }

    fun loadSearchPhotos() {
        viewModelScope.launch {
            _photosState.value = ApiResponse.Loading()
            try {
                val photos = repository.getSearchPhotos("Washroom")
                _photosState.value = ApiResponse.Success(photos)
            } catch (e: Exception) {
                _photosState.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun refreshData() {

        loadSearchPhotos()

    }
}