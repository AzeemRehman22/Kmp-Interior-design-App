// commonMain/kotlin/network/KtorClient.kt
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorClient {

    companion object {
        private const val BASE_URL = "https://api.unsplash.com"
        private const val ACCESS_KEY = "RvoO5j5M6xmzNM_6LUVZZQSNJwbfJe-EkbJCPwYYIxA" // Replace with your actual key
    }

    val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }


        install(HttpTimeout) {
            requestTimeoutMillis = 15000
            connectTimeoutMillis = 15000
            socketTimeoutMillis = 15000
        }

        defaultRequest {
//            url("https://jsonplaceholder.typicode.com/")
//            println("ApiUrl"+url)
//            header("Content-Type", "application/json")

            url("https://api.unsplash.com")
            println("ApiUrl"+url)
            header("Content-Type", "application/json")
        }
    }
}