package org.example.practicekmp.data

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashSearchResponse(
    val total: Int,
    val total_pages: Int,
    val results: List<ResultSearch>
)

@Serializable
data class ResultSearch(
    val id: String,
    val slug: String,
    val alternative_slugs: AlternativeSlugs,
    val created_at: String,
    val updated_at: String,
    val promoted_at: String?, // nullable
    val width: Int,
    val height: Int,
    val color: String,
    val blur_hash: String,
    val description: String?, // nullable
    val alt_description: String?,
    val breadcrumbs: List<Breadcrumb>? = emptyList(), // optional
    val urls: Urls,
    val links: Links,
    val likes: Int,
    val liked_by_user: Boolean,
    val bookmarked: Boolean,
    val current_user_collections: List<Collection>? = emptyList(),
    val sponsorship: Sponsorship? = null, // can be null
    val topic_submissions: Map<String, TopicSubmission>? = emptyMap(), // dynamic keys
    val asset_type: String,
    val user: UserX
)

@Serializable
data class TopicSubmission(
    val status: String,
    val approved_on: String?
)

@Serializable
data class Breadcrumb(
    val slug: String? = null,
    val title: String? = null
)

@Serializable
data class Collection(
    val id: String? = null,
    val title: String? = null,
    val published_at: String? = null,
    val last_collected_at: String? = null,
    val updated_at: String? = null
)

@Serializable
data class Sponsorship(
    val impression_urls: List<String>? = emptyList(),
    val tagline: String? = null,
    val tagline_url: String? = null,
    val sponsor: UserX? = null
)


@Serializable
data class AlternativeSlugs(
    val en: String,
    val es: String,
    val ja: String,
    val fr: String,
    val `it`: String,
    val ko: String,
    val de: String,
    val pt: String,
    val id: String
)

@Serializable
data class Links(
    val self: String,
    val html: String,
    val download: String,
    val download_location: String
)

@Serializable
data class Urls(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    val small_s3: String
)

@Serializable
data class UserX(
    val id: String,
    val updated_at: String,
    val username: String,
    val name: String,
    val first_name: String,
    val last_name: String?,
    val twitter_username: String?, // can be null
    val portfolio_url: String?,
    val bio: String?,
    val location: String?,
    val links: LinksX,
    val profile_image: ProfileImage,
    val instagram_username: String?,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val total_promoted_photos: Int,
    val total_illustrations: Int,
    val total_promoted_illustrations: Int,
    val accepted_tos: Boolean,
    val for_hire: Boolean,
    val social: Social
)

@Serializable
data class LinksX(
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String
)

@Serializable
data class ProfileImage(
    val small: String,
    val medium: String,
    val large: String
)

@Serializable
data class Social(
    val instagram_username: String?,
    val portfolio_url: String?,
    val twitter_username: String?,
    val paypal_email: String?
)