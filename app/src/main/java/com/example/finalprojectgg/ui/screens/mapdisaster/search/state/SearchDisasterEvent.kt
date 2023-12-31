package com.example.finalprojectgg.ui.screens.mapdisaster.search.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState

sealed class SearchDisasterEvent {
    data class FilterStateChanged @OptIn(ExperimentalMaterialApi::class) constructor(
        val filterState: ModalBottomSheetState
    ) : SearchDisasterEvent()
}