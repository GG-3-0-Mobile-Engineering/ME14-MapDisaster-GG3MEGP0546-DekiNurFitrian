package com.example.finalprojectgg.domain.repository

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface MapDisasterRepository {
    /**
     * Get offline first list recent of [ReportModel]. This operation use network resource bounds.
     */
    fun getReports(filterQuery:FilterActive): Flow<Resource<List<ReportModel>>>
    /**
     * Get list of [ReportModel] from network with query time period.
     */
    fun getReportsArchive(filterQuery: FilterActive):Flow<Resource<List<ReportModel>>>
    /**
     * Get filter that has active property in it.
     */
    fun getFilterActive():SharedFlow<FilterActive>
    /**
     * Get filter reference for displaying filter content.
     */
    fun getFilter():StateFlow<FilterState>
    /**
     * Get list of province by query that given
     */
    fun getProvinceByQuery(query: String): Flow<List<FilterProvinceModel>>
    /**
     * Updating filter active state. This take [FilterEvent] to indicate event triggered by UI.
     */
    fun updateFilterActive(event: FilterEvent)
}