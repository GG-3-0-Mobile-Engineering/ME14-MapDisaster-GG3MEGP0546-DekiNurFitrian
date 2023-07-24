package com.example.finalprojectgg.ui.screens.main

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.finalprojectgg.ui.components.CustomTextField
import com.example.finalprojectgg.ui.navigation.AppNavGraph
import com.example.finalprojectgg.ui.navigation.Screens
import com.example.finalprojectgg.ui.screens.main.components.DetailBarView
import com.example.finalprojectgg.ui.screens.main.components.SearchBarView
import com.example.finalprojectgg.ui.screens.main.state.TopAppBarState
import com.example.finalprojectgg.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination?.route
    var bottomBarState by rememberSaveable {
        mutableStateOf(false)
    }
    var topBarState by rememberSaveable {
        mutableStateOf(false)
    }
    val topBarAlphaState by viewModel.mapBottomSheetState.collectAsStateWithLifecycle()

    val topAppBarState = viewModel.topAppBarViewState



    when (currentDestination) {
        Screens.MapDisaster.route -> {
            bottomBarState = true
            topBarState = true
        }

        Screens.MapDisasterSearch.route -> {
            bottomBarState = false
            topBarState = true
        }

        Screens.Profile.route -> {
            bottomBarState = true
            topBarState = false
        }

        else -> {
            bottomBarState = false
            topBarState = false
        }
    }

    Scaffold(
        topBar = {
            AnimatedVisibility(visible = topBarState,
                enter = slideInVertically { -it },
                exit = slideOutVertically { -it }) {
                TopAppBar(
                    backgroundColor = MaterialTheme.colorScheme.surface.copy(alpha = topBarAlphaState),
                    elevation = 0.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                    ) {
                        Log.d("Main", "${topAppBarState.state}")
                        when (topAppBarState.state) {
                            TopAppBarState.SEARCH -> SearchBarView(
                                navController = navController,
                                searchEnabled = topAppBarState.searchEnabled
                            )

                            TopAppBarState.DETAIL -> DetailBarView()
                        }
                    }
                }
            }
        },
        bottomBar = {
            AnimatedVisibility(visible = bottomBarState,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }) {
                BottomBar(navController = navController)
            }
        },
    ) {
        Box(
            modifier = modifier.padding(bottom = it.calculateBottomPadding()),
        ) {
            AppNavGraph(paddingValues = it, navController = navController, viewModel = viewModel)
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController
) {
    val screens = listOf(
        Screens.MapDisaster, Screens.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
    ) {
        screens.map {
            NavigationBarItem(
                icon = {
                    Icon(imageVector = Icons.Default.Place, contentDescription = null)
                },
                label = {
                    Text(text = it.title, style = MaterialTheme.typography.labelMedium)
                },
                selected = currentDestination?.hierarchy?.any { navDestination ->
                    navDestination.route == it.route
                } == true,
                onClick = { navController.navigate(it.route) },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.secondaryContainer,
                    selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    selectedTextColor = MaterialTheme.colorScheme.onSurface,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}