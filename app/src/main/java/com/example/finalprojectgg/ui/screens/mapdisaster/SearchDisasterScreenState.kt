package com.example.finalprojectgg.ui.screens.mapdisaster

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue

data class SearchDisasterScreenState @OptIn(ExperimentalMaterialApi::class) constructor(
    var filterClicked: () -> Unit = {},
)
