package com.example.finalprojectgg.ui.screens.mapdisaster.search.state

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.ReportModel

data class SearchDisasterScreenState @OptIn(ExperimentalMaterialApi::class) constructor(
    var filterClicked: () -> Unit = {},
    var provinceSearch: List<FilterProvinceModel> = listOf(),
    var reportsRelated: List<ReportModel> = listOf()
)
