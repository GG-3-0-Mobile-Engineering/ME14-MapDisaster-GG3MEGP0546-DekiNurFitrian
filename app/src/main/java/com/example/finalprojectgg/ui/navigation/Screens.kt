package com.example.finalprojectgg.ui.navigation

sealed class Screens(val route:String){
    object Splash:Screens("Splash-Screen")
    object Main:Screens("Main-Screen")
    object Detail:Screens("Detail-Screen")
    object Setting:Screens("Setting-Screen")
}
