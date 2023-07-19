package com.example.finalprojectgg.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.finalprojectgg.ui.screens.MainScreen
import com.example.finalprojectgg.ui.screens.SplashScreen

@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {
        composable(
            route = Screens.Splash.route
        ) {
            SplashScreen(navToMainScreen = {
                navController.navigate(Screens.Main.route)
            })
        }

        composable(
            route = Screens.Main.route
        ) {
            MainScreen()
        }
    }
}