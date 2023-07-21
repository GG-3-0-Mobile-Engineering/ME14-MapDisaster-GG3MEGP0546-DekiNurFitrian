package com.example.finalprojectgg.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.contentColorFor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectgg.R
import com.example.finalprojectgg.ui.navigation.AppNavGraph
import com.example.finalprojectgg.ui.navigation.Screens
import com.example.finalprojectgg.ui.theme.md_theme_dark_primary

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MainScreen(
    modifier:Modifier = Modifier
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    Scaffold(bottomBar = {
        if (currentDestination != Screens.Detail.route) BottomBar(
            navController = navController
        )
    }) {
        Column(
            modifier = modifier
                .padding(it),
        ) {
            AppNavGraph(navController = navController)
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        Screens.MapDisaster,
        Screens.SearchDisaster,
        Screens.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.primary
    ) {
        screens.map {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_person_pin),
                        contentDescription = it.title,
                    )
                },
                label = {
                    Text(text = it.title)
                },
                selected = currentDestination?.hierarchy?.any { navDestination ->
                    navDestination.route == it.route
                } == true,
                onClick = { navController.navigate(it.route) },
                unselectedContentColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f) ,
                selectedContentColor = MaterialTheme.colorScheme.primary,
                alwaysShowLabel = false,
            )
        }
    }
}