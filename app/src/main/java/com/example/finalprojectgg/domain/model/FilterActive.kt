package com.example.finalprojectgg.domain.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.finalprojectgg.ui.screens.state.TimePeriod

data class FilterActive(
    var filterByDisaster: MutableList<String> = mutableListOf(),
    var filterByProvince: MutableList<String> = mutableListOf(),
    var filterByTimePeriod: TimePeriod? = null
)
