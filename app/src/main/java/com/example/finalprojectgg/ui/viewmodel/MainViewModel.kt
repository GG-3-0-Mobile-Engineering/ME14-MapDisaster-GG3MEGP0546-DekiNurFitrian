package com.example.finalprojectgg.ui.viewmodel

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finalprojectgg.ui.components.States
import com.example.finalprojectgg.ui.screens.main.state.TopAppBarState
import com.example.finalprojectgg.ui.screens.main.state.TopAppBarViewState
import com.example.finalprojectgg.ui.screens.mapdisaster.state.MapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel : ViewModel() {

    private var _mapBottomSheetState = MutableStateFlow(0f)
    val mapBottomSheetState = _mapBottomSheetState.asStateFlow()

    var mapState by mutableStateOf(MapState())

    var topAppBarViewState by mutableStateOf(TopAppBarViewState())

    @OptIn(ExperimentalMaterialApi::class)
    fun updateMapBottomSheetState(swipeableState: SwipeableState<States>) {
        val threshold by derivedStateOf { swipeableState.offset.value <= 100f }
        if (threshold) {
            _mapBottomSheetState.value = 1f - (swipeableState.offset.value / 100f)
        } else {
            _mapBottomSheetState.value = 0f
        }
    }

    fun updateTopAppBarState(listState: LazyListState) {
        val threshold by derivedStateOf { listState.firstVisibleItemScrollOffset <= 50 }
        topAppBarViewState = if (threshold) {
            topAppBarViewState.copy(state = TopAppBarState.SEARCH)
        } else {
            topAppBarViewState.copy(state = TopAppBarState.DETAIL)
        }
    }
}