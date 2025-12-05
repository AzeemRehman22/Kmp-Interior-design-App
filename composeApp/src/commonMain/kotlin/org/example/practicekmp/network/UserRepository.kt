package org.example.practicekmp.network

import UnsplashPhoto
import io.github.aakira.napier.Napier
import org.example.practicekmp.data.ApiResponse
import org.example.practicekmp.data.Photo
import org.example.practicekmp.data.Post

class PostRepository {
    private val apiService = ApiService()

    suspend fun getPosts(): ApiResponse<List<Post>> {
        return try {
            val posts = apiService.getPosts()
            if (posts.isNotEmpty()) {
                ApiResponse.Success(posts)
            } else {
                ApiResponse.Error("No posts found")
            }
        } catch (e: Exception) {
            ApiResponse.Error(e.message ?: "Unknown error occurred")
        }
    }

    suspend fun getPopularPhotos(): List<Photo> {
        val unsplashPhotos = apiService.getPopularPhotos()
        Napier.d("🛰️ API Response =${unsplashPhotos}")
        return unsplashPhotos.map { it.toPhoto() }
    }

    private fun UnsplashPhoto.toPhoto(): Photo {
        return Photo(
            id = this.id,
            title = this.description ?: this.alt_description ?: "Untitled",
            url = this.urls.regular, // Use regular size for good quality
            thumbnailUrl = this.urls.small,
            likes = this.likes,
            userName = this.user.name,
            userProfileImage = this.user.profile_image.medium
        )
    }


    suspend fun getSearchPhotos(query:String): List<Photo> {
        return try {
            val response = apiService.getSearchPhotos(query)
            //Napier.d("API Response total=${response.total}, results=${response.results.size}")
            Napier.d("Full response: $response")
            // Check if we have results
            if (response.results.isEmpty()) {
                Napier.w("API returned empty results list")
            }
            response.results.map { result ->
                Photo(
                    id = result.id,
                    title = result.description?.ifEmpty {
                        result.alt_description?.ifEmpty { "Untitled" }
                    },
                    url = result.urls.regular,
                    thumbnailUrl = result.urls.small,
                    likes = result.likes,
                    userName = result.user.name,
                    userProfileImage = result.user.profile_image.medium
                )
            }
        } catch (e: Exception) {
            Napier.e("API error: ${e.message}")
            emptyList()
        }
    }

}