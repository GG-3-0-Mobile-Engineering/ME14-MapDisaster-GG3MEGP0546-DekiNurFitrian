package com.example.finalprojectgg.domain.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.example.finalprojectgg.ui.screens.state.TimePeriod

data class FilterActive(
    val filterByDisaster: SnapshotStateList<String> = mutableStateListOf(),
    val filterByProvince: SnapshotStateList<String> = mutableStateListOf(),
    var filterByTimePeriod: TimePeriod? = null
)
