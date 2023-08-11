package com.example.finalprojectgg.ui.screens.state

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.listDisaster
import com.example.finalprojectgg.domain.model.listFilterProvince

data class FilterState(
    var disasterFilter: List<ChipModel> = listDisaster,
    var timePeriodFilter:TimePeriod = TimePeriod("",""),
    var provinceFilter: List<FilterProvinceModel> = listFilterProvince
)

data class TimePeriod(
    var startTime:String,
    var endTime:String
)
