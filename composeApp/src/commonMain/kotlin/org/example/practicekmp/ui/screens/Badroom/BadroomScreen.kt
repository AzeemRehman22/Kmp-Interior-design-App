package org.example.practicekmp.screens.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import io.github.aakira.napier.Napier
import org.example.practicekmp.data.ApiResponse
import org.example.practicekmp.data.Photo
import org.example.practicekmp.ui.screens.Badroom.BedroomViewModel
import org.example.practicekmp.utils.Utility


@Composable
fun BadroomScreen() {
    val viewModel: BedroomViewModel = viewModel()
    val photostate by viewModel.photosState.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with refresh button
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Posts",
                style = MaterialTheme.typography.headlineMedium
            )

            Button(
                onClick = { viewModel.refreshData() },
                enabled = photostate !is ApiResponse.Loading
            ) {
                if (photostate is ApiResponse.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Refresh")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Handle different states
        when (photostate) {
            is ApiResponse.Loading -> {
                Utility().LoadingIndicator()
            }
            is ApiResponse.Success -> {
                val photos = (photostate as ApiResponse.Success<List<Photo>>).data
                if (photos!!.isNotEmpty()) {

                    //PostList(posts = posts)

                    Napier.e("Displaying ${photos.size} posts")
                    PhotoList(photos)
                    //println("✅ Displaying ${posts.size} posts")
                } else {
                    Utility().ErrorMessage(error = "No posts found") {
                        viewModel.refreshData()
                    }
                }
            }
            is ApiResponse.Error -> {
                val errorMessage = (photostate as ApiResponse.Error).message
                Utility().ErrorMessage(error = errorMessage!!) {
                    viewModel.refreshData()
                }
            }
        }
    }
}


@Composable
fun PhotoList(photos: List<Photo>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(photos, key = { it.id }) { photo ->
            NetworkImageCard(photo)
        }
    }
}

@Composable
fun NetworkImageCard(photo: Photo) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            AsyncImage(
                model = photo.url, // 👈 this is your image URL from API
                contentDescription = photo.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
            )
            Text(
                text = photo.title ?: "Untitled",
                modifier = Modifier.padding(12.dp),
                fontWeight = FontWeight.Medium,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}



//@Composable
//fun LoadingIndicator() {
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.Center)
//    ) {
//        CircularProgressIndicator()
//    }
//}

//@Composable
//fun ErrorMessage(error: String, onRetry: () -> Unit) {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .wrapContentSize(Alignment.Center),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Text(
//            text = "Error: $error",
//            color = MaterialTheme.colorScheme.error,
//            textAlign = TextAlign.Center,
//            modifier = Modifier.padding(16.dp)
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = onRetry) {
//            Text("Retry")
//        }
//    }
//}













