package com.example.finalprojectgg.ui.navigation

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.finalprojectgg.R

sealed class Screens(
    val route: String,
    val title: String = "",
    val icon: ImageVector = Icons.Default.Circle
) {
    companion object {
        const val ROOT_ROUTE = "ROOT_ROUTE"
    }
    object Splash : Screens("Splash-Screen")
    object MapDisaster : Screens(
        "MapDisaster-Screen",
        "Map",
        Icons.Default.Place
    )
    object MapDisasterSearch : Screens("MapDisasterSearch-Screen")
    object Profile : Screens(
        "Profile-Screen",
        "Profile",
        Icons.Default.Person
    )

}

