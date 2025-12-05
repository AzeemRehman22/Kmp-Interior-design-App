// data/UnsplashModels.kt
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashResponse(
    val results: List<UnsplashPhoto>,
    val total: Int,
    val total_pages: Int
)

@Serializable
data class UnsplashPhoto(
    val id: String,
    val urls: UnsplashUrls,
    val user: UnsplashUser,
    val likes: Int,
    val description: String?,
    val alt_description: String?
)

@Serializable
data class UnsplashUrls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String
)

@Serializable
data class UnsplashUser(
    val id: String,
    val username: String,
    val name: String,
    val profile_image: UnsplashProfileImage
)

@Serializable
data class UnsplashProfileImage(
    val small: String,
    val medium: String,
    val large: String
)