package com.example.finalprojectgg.ui.screens.main.state

data class TopAppBarViewState(
    var searchEnabled: Boolean = false,
    var state: TopAppBarState = TopAppBarState.SEARCH
)

enum class TopAppBarState {
    SEARCH,
    DETAIL
}