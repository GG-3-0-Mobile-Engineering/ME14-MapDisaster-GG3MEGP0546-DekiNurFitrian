package com.example.finalprojectgg.ui.viewmodel

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.finalprojectgg.ui.navigation.Screens
import com.example.finalprojectgg.ui.screens.main.MainScreenEvent
import com.example.finalprojectgg.ui.screens.main.state.MainScreenViewState
import com.example.finalprojectgg.ui.screens.main.state.TopAppBarState
import com.example.finalprojectgg.ui.screens.mapdisaster.MapScreenEvent
import com.example.finalprojectgg.ui.screens.mapdisaster.SearchDisasterEvent
import com.example.finalprojectgg.ui.screens.mapdisaster.SearchDisasterScreenState
import com.example.finalprojectgg.ui.screens.mapdisaster.state.MapState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class MainViewModel : ViewModel() {

    var mapState by mutableStateOf(MapState())

    var mainScreenViewState = MutableStateFlow(MainScreenViewState())

    var searchDisasterScreenState = MutableStateFlow(SearchDisasterScreenState())

    @OptIn(ExperimentalMaterialApi::class)
    fun onMapScreenEvent(event: MapScreenEvent) {
        when (event) {
            is MapScreenEvent.BottomSheetChanged -> {
                val sheetOffset by event.swipeableState.offset
                val sheetOffsetThreshold by derivedStateOf { sheetOffset <= 100f }

                mainScreenViewState.value = if (sheetOffsetThreshold) {
                    mainScreenViewState.value.copy(topAppBarAlpha = 1f - (sheetOffset / 100f))
                } else {
                    mainScreenViewState.value.copy(topAppBarAlpha = 0f)
                }

                val sheetContentThreshold by derivedStateOf {
                    event.scrollState.firstVisibleItemIndex == 0 && event.scrollState.firstVisibleItemScrollOffset <= 50
                }
                mainScreenViewState.value = if (sheetContentThreshold) {
                    mainScreenViewState.value.copy(topAppBarState = TopAppBarState.SEARCH)
                } else {
                    mainScreenViewState.value.copy(topAppBarState = TopAppBarState.DETAIL)
                }
            }
        }
    }


    fun onMainScreenEvent(event: MainScreenEvent){
        when (event){
            is MainScreenEvent.ScreenChanged -> {
                mainScreenViewState.value = if (event.currentScreenActive == Screens.MapDisasterSearch.route){
                     mainScreenViewState.value.copy(searchEnabled = true)
                } else {
                    mainScreenViewState.value.copy(searchEnabled = false)
                }
            }
            is MainScreenEvent.FilterClicked -> {
                searchDisasterScreenState.value.filterClicked.invoke()
            }
        }
    }

}