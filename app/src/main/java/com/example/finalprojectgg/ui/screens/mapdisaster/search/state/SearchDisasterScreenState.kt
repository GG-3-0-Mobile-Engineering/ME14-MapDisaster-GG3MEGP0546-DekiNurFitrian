package com.example.finalprojectgg.ui.screens.mapdisaster.search.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue

data class SearchDisasterScreenState @OptIn(ExperimentalMaterialApi::class) constructor(
    var filterClicked: () -> Unit = {},
)
