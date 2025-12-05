package org.example.practicekmp.screens.Home

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.practicekmp.ui.theme.primaryDark
import org.jetbrains.compose.resources.painterResource
import practicekmp.composeapp.generated.resources.Logo_icon
import practicekmp.composeapp.generated.resources.Res
import practicekmp.composeapp.generated.resources.compose_multiplatform
import practicekmp.composeapp.generated.resources.logo


@Composable
fun ProfileScreen(
    contentPaddingValues: PaddingValues = PaddingValues(0.dp)
) {

    Column(modifier = Modifier.fillMaxSize().padding(contentPaddingValues)) {


        // Main content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painterResource(Res.drawable.Logo_icon), contentDescription = "", modifier = Modifier.size(150.dp))
            Spacer(modifier = Modifier.height(12.dp))

            WriteIconWithText("ID:000012")

            WriteIconWithText("Name:Azeem Rehman")

            WriteIconWithText("Contact:0345-3064093")

            WriteIconWithText("Address:DHA, Karachi")

        }

    }

}

@Composable
fun WriteIconWithText(text:String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Circle,
            contentDescription = "Setting",
            modifier = Modifier.size(22.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}










