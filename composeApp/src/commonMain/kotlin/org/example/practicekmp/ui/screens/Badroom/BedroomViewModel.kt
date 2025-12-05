package org.example.practicekmp.ui.screens.Badroom


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.example.practicekmp.data.ApiResponse
import org.example.practicekmp.data.Photo
import org.example.practicekmp.network.PostRepository

class BedroomViewModel : ViewModel() {
    private val repository = PostRepository()

//    private val _postsState = MutableStateFlow<ApiResponse<List<Post>>>(ApiResponse.Loading())
//    val postsState: StateFlow<ApiResponse<List<Post>>> = _postsState.asStateFlow()

    private val _photosState = MutableStateFlow<ApiResponse<List<Photo>>>(ApiResponse.Loading())
    val photosState: StateFlow<ApiResponse<List<Photo>>> = _photosState.asStateFlow()

    init {

        loadSearchPhotos()

       // loadPosts()
        //loadPhoto()
        //loadPopularPhotos()


    }

//    fun loadPosts() {
//        viewModelScope.launch {
//            _postsState.value = ApiResponse.Loading()
//            _postsState.value = repository.getPosts()
//        }
//    }

    fun loadPhoto() {
        viewModelScope.launch {
            _photosState.value = ApiResponse.Loading()
          //  _photosState.value = repository.getPhotos()
        }

    }

    fun loadPopularPhotos() {
        viewModelScope.launch {
            _photosState.value = ApiResponse.Loading()
            try {
                val photos = repository.getPopularPhotos()
                _photosState.value = ApiResponse.Success(photos)
            } catch (e: Exception) {
                _photosState.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun loadSearchPhotos() {
        viewModelScope.launch {
            _photosState.value = ApiResponse.Loading()
            try {
                val photos = repository.getSearchPhotos("Badroom")
                _photosState.value = ApiResponse.Success(photos)
            } catch (e: Exception) {
                _photosState.value = ApiResponse.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun refreshData() {

        loadSearchPhotos()

        //loadPosts()
        //loadPhoto()
        //loadPopularPhotos()

    }
}