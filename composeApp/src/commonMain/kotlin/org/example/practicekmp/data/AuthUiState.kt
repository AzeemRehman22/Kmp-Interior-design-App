package org.example.practicekmp.data

data class AuthUiState(

    val email : String = "",
    val password: String = "",

    val isLoggedIn: Boolean = false,
    val hasSeenSplash: Boolean = false,

    val isEmailValid: Boolean = false,
    val isPasswordValid: Boolean = false,

    val isPasswordVisible: Boolean = false,

    )
