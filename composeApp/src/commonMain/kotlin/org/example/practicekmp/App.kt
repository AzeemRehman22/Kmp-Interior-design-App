package org.example.practicekmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.BuildCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Spa
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import org.example.practicekmp.screens.Auth.LoginScreen
import org.example.practicekmp.screens.Auth.RegisterScreen
import org.example.practicekmp.screens.Home.HomeScreen
import org.example.practicekmp.screens.Splash.SplashScreen
import org.example.practicekmp.ui.theme.AppTheme
import org.example.practicekmp.ui.theme.primaryDark
import org.example.practicekmp.utils.ConstructionRoutes
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import org.example.practicekmp.screens.Home.BadroomScreen
import org.example.practicekmp.screens.Home.KitchenScreen
import org.example.practicekmp.screens.Home.LoungeScreen
import org.example.practicekmp.screens.Home.ProfileScreen
import org.example.practicekmp.screens.Home.SettingScreen
import org.example.practicekmp.screens.Home.StairScreen
import org.example.practicekmp.screens.Home.WashroomScreen
import org.example.practicekmp.ui.screens.Auth.LoginScreen.LoginViewModel
import org.example.practicekmp.ui.screens.Badroom.BedroomViewModel


@Composable
@Preview(showBackground = true)
fun App() {

    val loginViewModel: LoginViewModel = viewModel()
    val userViewModel: BedroomViewModel = viewModel()

    AppTheme {
        val navController = rememberNavController()
        val currentRout = navController.currentBackStackEntryAsState().value?.destination?.route

        println(currentRout)

        val noBottomBarRoutes = listOf(
            ConstructionRoutes.SPLASHSCREEN.route,
            ConstructionRoutes.LOGINSCREEN.route,
            ConstructionRoutes.REGISTERSCREEN.route
        )

        val noTopBarRoutes = listOf(
            ConstructionRoutes.SPLASHSCREEN.route,
            ConstructionRoutes.LOGINSCREEN.route,
            ConstructionRoutes.REGISTERSCREEN.route
        )

        val canNavigateBack = navController.previousBackStackEntry != null
        val title = when (currentRout) {
            ConstructionRoutes.HOMESCREEN.route -> "Home"
            ConstructionRoutes.KITCHENSCREEN.route -> "Kitchen"
            ConstructionRoutes.BADROOMSCREEN.route -> "Bedroom"
            ConstructionRoutes.LOUNGESCREEN.route -> "Lounge"
            ConstructionRoutes.STAIRSCREEN.route -> "Stair"
            ConstructionRoutes.WASHROOMSCREEN.route -> "Washroom"
            ConstructionRoutes.SETTINGSCREEN.route -> "Settings"
            ConstructionRoutes.PROFILESCREEN.route -> "Profile"
            else -> ""
        }


        Scaffold (
            topBar = {
                if (currentRout !in noTopBarRoutes) {
                    TopNavigationBar(
                        title = title,
                        canNavigateBack = canNavigateBack && currentRout != ConstructionRoutes.HOMESCREEN.route,
                        onNavigateBack = { navController.popBackStack() }
                    )
                }

            },
            bottomBar = {
            if(currentRout !in noBottomBarRoutes){
                BottomNavigation(navController)
            }

        }
            ){
            contentpadding ->
        androidx.navigation.compose.NavHost(navController, ConstructionRoutes.SPLASHSCREEN.route) {

            composable(
                ConstructionRoutes.SPLASHSCREEN.route,
            ) {
                SplashScreen(onNavigate = {
                    navController.navigate(ConstructionRoutes.LOGINSCREEN.route){
                        // Clear Splash from the backstack
                        popUpTo(ConstructionRoutes.SPLASHSCREEN.route) { inclusive = true }
                    }

                })
            }
            composable(
                ConstructionRoutes.LOGINSCREEN.route,
            ) {
                LoginScreen(onHome = {

                    if (loginViewModel.onLoginClick()){
                        navController.navigate(ConstructionRoutes.HOMESCREEN.route){
                            // Clear Splash from the backstack
                            popUpTo(ConstructionRoutes.LOGINSCREEN.route) { inclusive = true }
                        }
                    }else{
                        //android.widget.Toast.makeText(context, "Invalid Credential", Toast.LENGTH_SHORT).show()
                        println("Invalid credential")

                    }


                }, onSignup = {
                    navController.navigate(ConstructionRoutes.REGISTERSCREEN.route)
                },
                    loginViewModel
                )
            }
            composable(
                ConstructionRoutes.REGISTERSCREEN.route,
            ) {
                RegisterScreen(onNavigate = {
                    navController.popBackStack()
                })
            }
            composable(
                ConstructionRoutes.HOMESCREEN.route,
            ) {
                HomeScreen(
                    onKitchen = {
                        navController.navigate(ConstructionRoutes.KITCHENSCREEN.route)
                    },
                    onBadroom = {
                        navController.navigate(ConstructionRoutes.BADROOMSCREEN.route)
                    },
                    onLounge = {
                        navController.navigate(ConstructionRoutes.LOUNGESCREEN.route)
                    },
                    onStair = {
                        navController.navigate(ConstructionRoutes.STAIRSCREEN.route)
                    },
                    onWashroom = {
                        navController.navigate(ConstructionRoutes.WASHROOMSCREEN.route)
                    }

                )
            }

            composable(
                ConstructionRoutes.SETTINGSCREEN.route,
            ) {
                SettingScreen(contentpadding)
            }

            composable(
                ConstructionRoutes.PROFILESCREEN.route,
            ) {
                ProfileScreen(contentpadding)
            }

            composable(
                ConstructionRoutes.KITCHENSCREEN.route,
            ) {
                KitchenScreen(contentpadding)
            }

            composable(
                ConstructionRoutes.BADROOMSCREEN.route,
            ) {
                BadroomScreen()
            }

            composable(
                ConstructionRoutes.LOUNGESCREEN.route,
            ) {
                LoungeScreen(contentpadding)
            }

            composable(
                ConstructionRoutes.STAIRSCREEN.route,
            ) {
                StairScreen(contentpadding)
            }
            composable(
                ConstructionRoutes.WASHROOMSCREEN.route,
            ) {
                WashroomScreen(contentpadding)
            }
        }
    }

    }
}

/*For botttom navBar*/
@Composable
private fun BottomNavigation(navController: NavController, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(30.dp)
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier.fillMaxWidth(),
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Spa,
                        contentDescription = null
                    )
                },
                label = {
                    Text("HOME", color = primaryDark)
                },
                selected = true,
                onClick = {
                    navController.navigate(ConstructionRoutes.HOMESCREEN.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = null
                    )
                },
                label = {
                    Text("PROFILE", color = primaryDark)
                },
                selected = false,
                onClick = {
                    navController.navigate(ConstructionRoutes.PROFILESCREEN.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                },
                label = {
                    Text("SETTING", color = primaryDark)
                },
                selected = false,
                onClick = {
                    navController.navigate(ConstructionRoutes.SETTINGSCREEN.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}


/*For top bar*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(
    title: String,
    canNavigateBack: Boolean,
    onNavigateBack: () -> Unit
) {
    TopAppBar(
        title = { Text(title) },
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            } else {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Home"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* You can add settings, profile, etc. */ }) {
                Icon(
                    imageVector = Icons.Default.BuildCircle,
                    contentDescription = "Action Icon"
                )
            }
        }

    )
}
