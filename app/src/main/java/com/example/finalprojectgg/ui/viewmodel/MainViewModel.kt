package com.example.finalprojectgg.ui.viewmodel

import android.util.Log
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import com.example.finalprojectgg.domain.usecase.MapDisasterUseCase
import com.example.finalprojectgg.domain.usecase.MapDisasterUseCaseImpl
import com.example.finalprojectgg.ui.navigation.Screens
import com.example.finalprojectgg.ui.screens.main.MainScreenEvent
import com.example.finalprojectgg.ui.screens.main.state.MainScreenViewState
import com.example.finalprojectgg.ui.screens.main.state.TopAppBarState
import com.example.finalprojectgg.ui.screens.mapdisaster.map.MapStyle
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapScreenEvent
import com.example.finalprojectgg.ui.screens.mapdisaster.search.state.SearchDisasterScreenState
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapState
import com.example.finalprojectgg.ui.screens.profile.state.ProfileScreenEvent
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mapDisasterUseCase: MapDisasterUseCase,
    private val repo: MapDisasterRepository
) :
    ViewModel() {
    val mapScreenViewState = MutableStateFlow(MapState())

    val mainScreenViewState = MutableStateFlow(MainScreenViewState())

    val searchDisasterScreenViewState = MutableStateFlow(SearchDisasterScreenState())

    val themeState = mutableStateOf(false)

    val filterState = mapDisasterUseCase.getFilterActive()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), FilterState())

    fun onFilterEvent(event: FilterEvent) = repo.updateFilterActive(event)

    init {
        viewModelScope.launch {
            mapDisasterUseCase.getReports().collectLatest { resource ->
                when (resource) {
                    is Resource.Success -> {
                        mapScreenViewState.update { state ->
                            state.copy(isProgress = false, reportModels = resource.data)
                        }
                    }

                    is Resource.Empty -> {
                        mapScreenViewState.update { state ->
                            state.copy(isEmpty = true)
                        }

                    }

                    is Resource.Loading -> {
                        mapScreenViewState.update { state ->
                            state.copy(isProgress = true)
                        }
                    }

                    is Resource.Error -> {
                        mapScreenViewState.update { state ->
                            state.copy(isError = true)
                        }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
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

            is MapScreenEvent.GetReport -> {
                mapDisasterUseCase.getReports()
            }
        }
    }


    @OptIn(FlowPreview::class)
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

            is MainScreenEvent.SearchChanged -> {
                viewModelScope.launch {
                    repo.getProvinceByQuery(event.query).debounce(200).collect {
                        Log.d("Search", it.toString())
                        searchDisasterScreenViewState.update { state ->
                            state.copy(provinceSearch = it)
                        }
                    }
                }
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
}