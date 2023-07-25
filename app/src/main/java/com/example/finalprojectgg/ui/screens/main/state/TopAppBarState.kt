package com.example.finalprojectgg.ui.screens.main.state

data class MainScreenViewState(
    var searchEnabled: Boolean = false,
    var topAppBarAlpha: Float = 0f,
    var topAppBarState: TopAppBarState = TopAppBarState.SEARCH,
)

enum class TopAppBarState {
    SEARCH,
    DETAIL
}