package com.example.finalprojectgg.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalprojectgg.ui.screens.MainScreen
import com.example.finalprojectgg.ui.screens.MapDisasterScreen
import com.example.finalprojectgg.ui.screens.ProfileScreen
import com.example.finalprojectgg.ui.screens.SearchDisasterScreen
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
                navController.navigate(Screens.ROOT_ROUTE)
            })
        }

        navigation(
            startDestination = Screens.MapDisaster.route,
            route = Screens.ROOT_ROUTE
        ) {
            composable(
                route = Screens.MapDisaster.route
            ) {
                MapDisasterScreen()
            }
            composable(
                route = Screens.SearchDisaster.route
            ) {
                SearchDisasterScreen()
            }
            composable(
                route = Screens.Profile.route
            ) {
                ProfileScreen()
            }

        }
    }
}