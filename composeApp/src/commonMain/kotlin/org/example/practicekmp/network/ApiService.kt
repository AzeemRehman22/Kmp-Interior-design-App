package org.example.practicekmp.network

import KtorClient
import UnsplashPhoto
import io.github.aakira.napier.Napier
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json
import org.example.practicekmp.data.Post
import org.example.practicekmp.data.ResultSearch
import org.example.practicekmp.data.UnsplashSearchResponse

class ApiService {
    private val client = KtorClient().httpClient

    companion object {
        private const val BASE_URL = "https://api.unsplash.com/"
        private const val ACCESS_KEY = "RvoO5j5M6xmzNM_6LUVZZQSNJwbfJe-EkbJCPwYYIxA" // Replace with your actual key
    }


    suspend fun getPosts(): List<Post> {
        return try {
            println("🚀 Starting API call to get posts...")

            val response = client.get("posts")
            println("📡 Response status: ${response.status}")

            // Check if response is successful
            if (!response.status.isSuccess()) {
                println("❌ HTTP Error: ${response.status}")
                return emptyList()
            }

            // Method 1: Get response as string and decode manually
            val responseString: String = response.body()
            println("📦 Raw response received, length: ${responseString.length}")

            // Manual JSON decoding
            val posts = Json.decodeFromString<List<Post>>(responseString)
            println("✅ Successfully parsed ${posts.size} posts")
            Napier.e { "images : $posts" }
            // Print first post if available
            if (posts.isNotEmpty()) {
                println("📝 First post title: ${posts.first().title}")
            }

            posts
        } catch (e: ClientRequestException) {
            println("🔴 Client error: ${e.response.status} - ${e.message}")
            emptyList()
        } catch (e: ServerResponseException) {
            println("🔴 Server error: ${e.response.status} - ${e.message}")
            emptyList()
        } catch (e: Exception) {
            println("🔴 Unexpected error: ${e.message}")
            e.printStackTrace()
            emptyList()
        }
    }

//    suspend fun getPhotos(): List<Photo> {
//        return try {
//            println("🚀 Starting API call to get posts...")
//
//            val response = client.get("photos")
//                println("📡 Response status: ${response.status}")
//
//            // Check if response is successful
//            if (!response.status.isSuccess()) {
//                println("❌ HTTP Error: ${response.status}")
//                return emptyList()
//            }
//
//            // Method 1: Get response as string and decode manually
//            val responseString: String = response.body()
//            println("📦 Raw response received, length: ${responseString.length}")
//
//            // Manual JSON decoding
//            val posts = Json.decodeFromString<List<Photo>>(responseString)
//            println("✅ Successfully parsed ${posts.size} posts")
//
//            // Print first post if available
//            if (posts.isNotEmpty()) {
//                println("📝 First post title: ${posts.first().title}")
//            }
//
//            posts
//        } catch (e: ClientRequestException) {
//            println("🔴 Client error: ${e.response.status} - ${e.message}")
//            emptyList()
//        } catch (e: ServerResponseException) {
//            println("🔴 Server error: ${e.response.status} - ${e.message}")
//            emptyList()
//        } catch (e: Exception) {
//            println("🔴 Unexpected error: ${e.message}")
//            e.printStackTrace()
//            emptyList()
//        }
//    }

    suspend fun getPopularPhotos(
        page: Int = 1,
        perPage: Int = 20
    ): List<UnsplashPhoto> {
        return try {
            client.get("$BASE_URL/photos") {
                headers {
                    append(HttpHeaders.Authorization, "Client-ID $ACCESS_KEY")
                }
                parameter("query", "popular")
                parameter("page", page)
                parameter("per_page", perPage)
            }.body()
        } catch (e: Exception) {
            emptyList()
        }
    }

//    suspend fun getRoomPhotos(
//        roomType: String = "kitchen",
//        style: String = "interior",
//        page: Int = 1,
//        perPage: Int = 20
//    ): List<Result> {
//        return try {
//            val query = "$roomType $style"
//            client.get("$BASE_URL/search/photos") {
//                headers {
//                    append(HttpHeaders.Authorization, "Client-ID $ACCESS_KEY")
//                }
//                parameter("query", query)
//                parameter("page", page)
//                parameter("per_page", perPage)
//            }.body()
//        } catch (e: Exception) {
//            emptyList()
//        }
//    }
//suspend fun getRoomPhotos(
//    roomType: String = "kitchen",
//    //style: String = "interior",
//    page: Int = 1,
//    perPage: Int = 20
//): UnsplashSearchResponse {
//    return try {
//
//        // Debug: Check if ACCESS_KEY is set
//        Napier.d("🔑 Access Key present: ${ACCESS_KEY.isNotEmpty()}")
//        Napier.d("🔑 Access Key starts with: ${ACCESS_KEY.take(10)}...")
//        //val query = "$roomType $style" // ✅ Combine both for more accurate search results
//        client.get("${BASE_URL}search/photos") {
//            Napier.d("🌐 Request URL: ${BASE_URL}search/photos?query=$roomType&page=$page&per_page=$perPage")
//            headers {
//                append(HttpHeaders.Authorization, "Client-ID $ACCESS_KEY")
//            }
//            parameter("query", roomType) // ✅ use combined query instead of hardcoded "kitchen"
//            parameter("page", page)
//            parameter("per_page", perPage)
//        }.body<UnsplashSearchResponse>() // ✅ Deserialize the full object
//    } catch (e: Exception) {
//        // ✅ Always match constructor parameter order (total, total_pages, results)
//        UnsplashSearchResponse(
//            total = 0,
//            total_pages = 0,
//            results = emptyList()
//        )
//    }
//}
suspend fun getSearchPhotos(
    roomType: String,
    page: Int = 1,
    perPage: Int = 20
): UnsplashSearchResponse { // ✅ Change return type to UnsplashSearchResponse
    return try {
        client.get("${BASE_URL}search/photos") {
            Napier.d("Request URL: ${BASE_URL}search/photos?query=$roomType&page=$page&per_page=$perPage")
            headers {
                append(HttpHeaders.Authorization, "Client-ID $ACCESS_KEY")
            }
            parameter("query", roomType) // ✅ Use roomType instead of "popular"
            parameter("page", page)
            parameter("per_page", perPage)
        }.body()
    } catch (e: Exception) {
        Napier.e("API Error: ${e.message}")
        // Return empty response instead of throwing to maintain compatibility
        UnsplashSearchResponse(0, 0, emptyList())
    }
}



}