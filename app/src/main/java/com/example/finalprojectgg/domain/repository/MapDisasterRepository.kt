package com.example.finalprojectgg.domain.repository

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

interface MapDisasterRepository {
    fun getReports(filterQuery:FilterActive): Flow<Resource<List<ReportModel>>>
    fun getReportsArchive(filterQuery: FilterActive):Flow<Resource<List<ReportModel>>>
    fun getFilterActive():SharedFlow<FilterActive>
    fun getFilter():MutableStateFlow<FilterState>
    fun updateFilterActive(event: FilterEvent)
}