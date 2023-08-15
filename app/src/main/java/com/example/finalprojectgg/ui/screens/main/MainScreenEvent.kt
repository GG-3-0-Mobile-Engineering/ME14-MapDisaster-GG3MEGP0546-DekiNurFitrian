package com.example.finalprojectgg.ui.screens.main

sealed class MainScreenEvent {
    data class SearchChanged(val query: String) : MainScreenEvent()
    data class ScreenChanged(val currentScreenActive: String?) : MainScreenEvent()
    object FilterClicked : MainScreenEvent()

}