package com.example.finalprojectgg.domain.usecase

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.ui.screens.state.FilterState
import kotlinx.coroutines.flow.Flow

interface MapDisasterUseCase {
    fun getReports():Flow<Resource<List<ReportModel>>>
    fun getFilterActive():Flow<FilterState>

}