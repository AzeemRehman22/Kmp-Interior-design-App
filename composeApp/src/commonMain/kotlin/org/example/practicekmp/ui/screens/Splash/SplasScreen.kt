package org.example.practicekmp.screens.Splash

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.example.practicekmp.ui.theme.primaryDark
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import practicekmp.composeapp.generated.resources.Logo_icon
import practicekmp.composeapp.generated.resources.Res
import practicekmp.composeapp.generated.resources.logo


@Composable
fun SplashScreen(
    onNavigate: () -> Unit
) {

    AnimatedTextScreen()
    // Launch side-effect when SplashScreen is first composed
    LaunchedEffect(Unit) {
        delay(3_000) // 3 seconds
        onNavigate()  // Navigate after delay
    }

}

@Preview(showBackground = true)
@Composable
fun Splashscreen(){
    Splashscreen()
}

@Composable
fun AnimatedTextScreen() {
    val scope = rememberCoroutineScope()

    var showTexts by remember { mutableStateOf(false) }

    // Trigger animation once screen loads
    LaunchedEffect(Unit) {
        showTexts = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(painterResource(Res.drawable.Logo_icon), contentDescription = "", modifier = Modifier.size(150.dp))
        // Animate "Construction" from Left
        AnimatedVisibility(
            visible = showTexts,
            enter = slideInHorizontally(initialOffsetX = { -it }) + fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = "Interior",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,

            )
        }

        // Animate "Design" from Right
        AnimatedVisibility(
            visible = showTexts,
            enter = slideInHorizontally(initialOffsetX = { it }) + fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = "Design",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = primaryDark

            )
        }
    }
}







