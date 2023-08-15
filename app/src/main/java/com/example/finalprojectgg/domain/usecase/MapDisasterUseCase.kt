package com.example.finalprojectgg.domain.usecase

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.ui.screens.state.FilterState
import kotlinx.coroutines.flow.Flow

interface MapDisasterUseCase {

    /**
     * Get all report that already processed and ready to use in UI layer.
     */
    fun getReports():Flow<Resource<List<ReportModel>>>
    /**
     * Get filter that already processed and ready to use in UI layer.
     */
    fun getFilterActive():Flow<FilterState>

}