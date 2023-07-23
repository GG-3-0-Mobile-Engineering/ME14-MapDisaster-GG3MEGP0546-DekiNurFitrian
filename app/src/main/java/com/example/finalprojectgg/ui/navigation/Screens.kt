package com.example.finalprojectgg.ui.navigation

import androidx.annotation.DrawableRes
import com.example.finalprojectgg.R

sealed class Screens(
    val route: String,
    val title: String = "",
    @DrawableRes val icon: Int = 0
) {
    companion object {
        const val ROOT_ROUTE = "ROOT_ROUTE"
    }

    object Splash : Screens("Splash-Screen")
    object MapDisaster : Screens(
        "MapDisaster-Screen",
        "Map",
        R.drawable.ic_person_pin
    )

    object SearchDisaster : Screens(
        "SearchDisaster-Screen",
        "Search",
        R.drawable.ic_person_pin
    )

    object MapDisasterSearch : Screens("MapDisasterSearch-Screen")
    object Detail : Screens("Detail-Screen")
    object Profile : Screens(
        "Profile-Screen",
        "Profile",
        R.drawable.ic_person_pin
    )

}

