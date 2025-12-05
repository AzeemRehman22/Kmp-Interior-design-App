package org.example.practicekmp.data

import kotlinx.serialization.Serializable

@Serializable
data class Interiors(
    val approved_on: String,
    val status: String
)