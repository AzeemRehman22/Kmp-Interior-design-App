package org.example.practicekmp.screens.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn // CHANGE: Import LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.practicekmp.ui.theme.primaryLight
import org.example.practicekmp.ui.theme.secondaryDark
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import practicekmp.composeapp.generated.resources.BadroomDesign
import practicekmp.composeapp.generated.resources.Res
import practicekmp.composeapp.generated.resources.StairDesign
import practicekmp.composeapp.generated.resources.building
import practicekmp.composeapp.generated.resources.bunglow
import practicekmp.composeapp.generated.resources.farmhouse
import practicekmp.composeapp.generated.resources.kitchenDesign
import practicekmp.composeapp.generated.resources.loungeDesign
import practicekmp.composeapp.generated.resources.park
import practicekmp.composeapp.generated.resources.swimming
import practicekmp.composeapp.generated.resources.washroomDesign


@Composable
fun HomeScreen(
    onKitchen: () -> Unit,
    onBadroom: () -> Unit,
    onLounge: () -> Unit,
    onStair: () -> Unit,
    onWashroom: () -> Unit,
    contentPaddingValues: PaddingValues = PaddingValues(0.dp)
) {
    // CHANGE: Use LazyColumn for the entire screen to allow vertical scrolling of all content.
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPaddingValues)
    ) {
        // Item 1: Search Bar
        item {
            Spacer(modifier = Modifier.height(20.dp))
            SearchBar(modifier = Modifier.padding(horizontal = 20.dp))
        }

        // Item 2: Horizontal scrolling categories
        item {
            AlignYourBodyRow { itemTitle ->
                // Handle item click here
                when (itemTitle) {
                    "Kitchen" -> {
                        onKitchen()
                        println("Kitchen clicked")
                    }
                    "BadRoom" -> {
                        onBadroom()
                        println("Bedroom clicked")
                    }
                    "Lounge" -> {
                        onLounge()
                        println("Lounge clicked")
                    }
                    "Stairs" -> {
                        onStair()
                        println("Stair clicked")
                    }
                    "WashRoom" -> {
                        onWashroom()
                        println("Washroom clicked")
                    }

                    // Add cases for other items
                }
            }
        }

        // Item 3: The vertical grid of rooms
        item {
            RoomGridScreen { itemTitle ->
                when (itemTitle) {
                    "BUNGALOW" -> {
                        onKitchen()
                        println("BUNGALOW clicked")
                    }
                    "BUILDING" -> {
                        onKitchen()
                        println("BUILDING clicked")
                    }
                }
            }
        }

        // Add a final spacer for better padding at the bottom
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}



@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text("Search here")
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}

@Composable
fun AlignYourBodyElement(
    imageRes: DrawableResource,
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        Color.Transparent
    }

    Column(
        modifier = modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null // Remove default ripple if you want custom feedback
            ) { onClick() }
            .background(backgroundColor, CircleShape)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = "Image of $title",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(title, color = primaryLight)
    }
}


@Composable
fun AlignYourBodyRow(
    onItemClick: (String) -> Unit = {} // Optional callback with item title
) {
    val items = listOf(
        Pair(Res.drawable.kitchenDesign, "Kitchen"),
        Pair(Res.drawable.BadroomDesign, "BedRoom"),
        Pair(Res.drawable.loungeDesign, "Lounge"),
        Pair(Res.drawable.StairDesign, "Stairs"),
        Pair(Res.drawable.washroomDesign, "WashRoom")
    )

    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items.forEach { (imageRes, title) ->
            AlignYourBodyElement(imageRes = imageRes, title = title,
                onClick = {
                    onItemClick(title) // Pass which item was clicked
                }
            )
        }
    }
}

@Composable
fun RoomGridScreen(
    onItemClick: (String) -> Unit = {} // Optional callback with item title
) {
    val items = listOf(
        Pair(Res.drawable.bunglow, "BUNGALOW"),
        Pair(Res.drawable.building, "BUILDING"),
        Pair(Res.drawable.farmhouse, "FARMHOUSE"),
        Pair(Res.drawable.swimming, "SWIMMING"),
        Pair(Res.drawable.washroomDesign, "FACTORY"),
        Pair(Res.drawable.park, "PARK")
    )

    // CHANGE: The grid is no longer trying to fill the whole screen.
    // It's also not scrollable itself, because the parent LazyColumn handles scrolling.
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .height(440.dp) // CHANGE: Give the grid a fixed height. Adjust as needed.
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        // The grid should not scroll independently.
        userScrollEnabled = false
    ) {
        items(items) { (imageRes, title) ->
            RoomCard(imageRes = imageRes, title = title,
                onClick = {
                    onItemClick(title) // Pass which item was clicked
                }
                )
        }
    }
}

@Composable
fun RoomCard(imageRes: DrawableResource, title: String, onClick: () -> Unit,) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { /* Handle click */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = title,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = secondaryDark
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onKitchen = {}, onBadroom = {}, onLounge = {}, onStair = {}, onWashroom = {})
}