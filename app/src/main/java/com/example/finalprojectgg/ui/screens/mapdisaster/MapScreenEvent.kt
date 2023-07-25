package com.example.finalprojectgg.ui.screens.mapdisaster

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import com.example.finalprojectgg.ui.components.States

sealed class MapScreenEvent {
    data class BottomSheetChanged @OptIn(ExperimentalMaterialApi::class) constructor(
        val swipeableState: SwipeableState<States>,
        val scrollState:LazyListState
    ) : MapScreenEvent()
}
