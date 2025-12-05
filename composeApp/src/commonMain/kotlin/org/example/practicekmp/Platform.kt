package org.example.practicekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform