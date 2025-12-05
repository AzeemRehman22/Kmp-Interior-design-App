import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.gradle.api.tasks.Sync

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    kotlin("plugin.serialization") version "2.0.0"
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val xcf = XCFramework() // <--- To combine multiple iOS targets into one XCFramework

    listOf(
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            xcf.add(this)
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation("io.ktor:ktor-client-android:2.3.5") // For Android
            implementation("io.ktor:ktor-client-android:2.3.5")


        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            /*for navigation and material icon*/
            implementation("org.jetbrains.androidx.navigation:navigation-compose:2.9.0")
            implementation("org.jetbrains.compose.material3:material3:1.6.0")
            implementation("org.jetbrains.compose.material:material-icons-extended:1.6.0")


            implementation("io.ktor:ktor-client-core:2.3.5")
            implementation("io.ktor:ktor-client-json:2.3.5")
            implementation("io.ktor:ktor-client-serialization:2.3.5")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.5")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.5")

            // Kotlin Serialization
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

            // Use a multiplatform logging library like Napier. as a log
            implementation("io.github.aakira:napier:2.6.1")

            implementation(libs.coil.compose.core)
            implementation(libs.coil.mp)
            implementation(libs.coil.network.ktor)
            implementation(libs.coil.compose)
            // for load image url
            //implementation("io.coil-kt:coil-compose-m3:2.6.0") // <-- ADD THIS
            //implementation("io.coil-kt:coil-network-ktor:2.6.0")

        }

        iosMain.dependencies {
            // Ktor for iOS
            implementation("io.ktor:ktor-client-darwin:2.3.5")
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "org.example.practicekmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.practicekmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}


/**
 * ✅ Custom Gradle task for Xcode integration
 * This generates and copies the iOS XCFramework into a single folder you can easily import into Xcode.
 */
tasks.register<Sync>("packForXcode") {
    group = "build"
    val buildType = "Release"
    val targetDir = File(buildDir, "xcode-frameworks")

    val mode = buildType.lowercase()
    val frameworkDir = File(buildDir, "XCFrameworks/$mode")

    dependsOn("assemble${buildType}")
    from(frameworkDir)
    into(targetDir)
}
