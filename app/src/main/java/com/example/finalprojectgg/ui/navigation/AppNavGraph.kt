package com.example.finalprojectgg.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
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
import com.example.finalprojectgg.ui.view_model.MainViewModel

@Composable
fun AppNavGraph(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = Screens.Splash.route) {

        composable(
            route = Screens.Splash.route
        ) {
            SplashScreen {
                navController.navigate(Screens.ROOT_ROUTE)
            }

        }

        navigation(
            startDestination = Screens.MapDisaster.route,
            route = Screens.ROOT_ROUTE
        ) {

            composable(
                route = Screens.MapDisaster.route
            ) {
                MapDisasterScreen(paddingValues, viewModel) {
                    navController.navigate(Screens.MapDisasterSearch.route)
                }
            }
            composable(
                route = Screens.MapDisasterSearch.route
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