package com.example.finalprojectgg.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.finalprojectgg.ui.screens.mapdisaster.map.MapDisasterScreen
import com.example.finalprojectgg.ui.screens.profile.ProfileScreen
import com.example.finalprojectgg.ui.screens.mapdisaster.search.SearchDisasterScreen
import com.example.finalprojectgg.ui.screens.splash.SplashScreen
import com.example.finalprojectgg.ui.viewmodel.MainViewModel

@RequiresApi(Build.VERSION_CODES.O)
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
                MapDisasterScreen(paddingValues, viewModel)
            }
            composable(
                route = Screens.MapDisasterSearch.route
            ) {
                SearchDisasterScreen(
                    modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
                    viewModel = viewModel
                )
            }
            composable(
                route = Screens.Profile.route
            ) {
                ProfileScreen(viewModel)
            }

        }
    }
}