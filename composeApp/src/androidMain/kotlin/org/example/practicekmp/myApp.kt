package org.example.practicekmp

import android.app.Application
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import io.ktor.http.ContentType

class myApp: Application() {
    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog())  // ✅ Enable Napier for Android logs
    }

}