package com.example.finalprojectgg.ui.viewmodel

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.domain.model.Report
import com.example.finalprojectgg.domain.model.listDisaster
import com.example.finalprojectgg.domain.model.listReportDummy
import com.example.finalprojectgg.domain.usecase.MapDisasterUseCase
import com.example.finalprojectgg.ui.navigation.Screens
import com.example.finalprojectgg.ui.screens.main.MainScreenEvent
import com.example.finalprojectgg.ui.screens.main.state.MainScreenViewState
import com.example.finalprojectgg.ui.screens.main.state.TopAppBarState
import com.example.finalprojectgg.ui.screens.mapdisaster.map.MapStyle
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapScreenEvent
import com.example.finalprojectgg.ui.screens.mapdisaster.search.state.SearchDisasterScreenState
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapState
import com.example.finalprojectgg.ui.screens.profile.state.ProfileScreenEvent
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class MainViewModel @Inject constructor(private val mapDisasterUseCase: MapDisasterUseCase) :
    ViewModel() {
    val predicates = listOf(
        { value: String -> value == "Banjir" },
        { value: String -> value == "Gempabumi" },
    )
    var mapScreenViewState = MutableStateFlow(MapState())
        private set
    var mainScreenViewState = MutableStateFlow(MainScreenViewState())
        private set
    var searchDisasterScreenViewState = MutableStateFlow(SearchDisasterScreenState())
        private set
    var themeState = mutableStateOf(false)
        private set

    var list = mutableStateListOf<ChipModel>()

    var filterState = mutableStateListOf<ChipModel>().apply {
        addAll(listDisaster)
    }
        private set

    var filterStateFlow = MutableStateFlow(listDisaster.toMutableList())
        private set

    init {
        Log.d("DATA VM", "INIT")
        collectFlow()
    }


    private fun collectFlow() {
        viewModelScope.launch {
            mapDisasterUseCase.getReports().collect { value ->
                when (value) {
                    is Resource.Loading -> {
                        Log.d("DATA VM", "Loading")
                        mapScreenViewState.update {
                            it.copy(isProgress = true, isContent = false)
                        }
                    }

                    is Resource.Success -> {
                        Log.d("DATA VM", "Success")
                        mapScreenViewState.update { mapState ->
                            mapState.copy(
                                data = value.data?.filter { report ->
                                    predicates.all {
                                        it(report.category)
                                    }
                                } ?: emptyList(),
                                isProgress = false,
                                isContent = true
                            )
                        }
                    }

                    is Resource.Error -> {
                        Log.d("DATA VM", "Error")
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class, FlowPreview::class)
    fun onMapScreenEvent(event: MapScreenEvent) {
        when (event) {
            is MapScreenEvent.BottomSheetChanged -> {
                val sheetOffset by event.swipeableState.offset
                val sheetOffsetThreshold by derivedStateOf { sheetOffset <= 100f }

                mainScreenViewState.value = if (sheetOffsetThreshold) {
                    mainScreenViewState.value.copy(topAppBarAlpha = 1f - (sheetOffset / 100f))
                } else if (sheetOffset == 0f) {
                    mainScreenViewState.value.copy(topAppBarAlpha = 1f)
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

            is MapScreenEvent.getReports -> {

            }
        }
    }


    fun onMainScreenEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.ScreenChanged -> {
                mainScreenViewState.value =
                    if (event.currentScreenActive == Screens.MapDisasterSearch.route) {
                        mainScreenViewState.value.copy(searchEnabled = true)
                    } else {
                        mainScreenViewState.value.copy(searchEnabled = false)
                    }
            }

            is MainScreenEvent.FilterClicked -> {
                searchDisasterScreenViewState.value.filterClicked.invoke()
            }
        }
    }

    fun onProfileScreenEvent(event: ProfileScreenEvent) {
        when (event) {
            is ProfileScreenEvent.ThemeSwitchChanged -> {
                themeState.value = !themeState.value
                if (themeState.value) {
                    mapScreenViewState.value = mapScreenViewState.value.copy(
                        properties = MapProperties(
                            mapStyleOptions = MapStyleOptions(MapStyle.darkMapStyle)
                        )
                    )
                } else {
                    mapScreenViewState.value = mapScreenViewState.value.copy(
                        properties = MapProperties(
                            mapStyleOptions = MapStyleOptions(MapStyle.lightMapStyle)
                        )
                    )
                }
            }
        }
    }

    fun onChipChanged(index: Int) {
        val item = filterState[index]
        val selected = item.selected
        filterState[index] = item.copy(selected = !selected)
    }

    fun onChipChangedSec(chip: ChipModel) {
        val selected = filterStateFlow.value.contains(chip)
        val newItem = filterStateFlow.value
        if (selected) newItem.remove(chip) else newItem.add(chip)
        filterStateFlow.value = newItem
        

        Log.d("Filter", filterStateFlow.value.hashCode().toString())
    }

}