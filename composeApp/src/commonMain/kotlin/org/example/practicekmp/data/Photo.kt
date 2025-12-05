package org.example.practicekmp.data

import kotlinx.serialization.Serializable

@Serializable
data class Photo(
    val id: String,
    val title: String?,
    val url: String,
    val thumbnailUrl: String?,
    val likes: Int = 0,
    val userName: String = "",
    val userProfileImage: String? = null
)

