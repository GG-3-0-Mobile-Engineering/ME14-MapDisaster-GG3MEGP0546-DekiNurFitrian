package com.example.finalprojectgg.ui.view_model

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finalprojectgg.ui.components.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class MainViewModel : ViewModel() {

    private var _mapBottomSheetState = MutableStateFlow(0f)
    val mapBottomSheetState = _mapBottomSheetState.asStateFlow()

    @OptIn(ExperimentalMaterialApi::class)
    fun updateMapBottomSheetState(swipeableState: SwipeableState<States>) {
        val animationThreshold = 100f
        if (swipeableState.offset.value <= animationThreshold) {
            _mapBottomSheetState.value = 1f - (swipeableState.offset.value / animationThreshold)
        } else {
            _mapBottomSheetState.value = 0f
        }
    }
}