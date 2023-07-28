package com.example.finalprojectgg.domain.usecase

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface MapDisasterUseCase {
    fun getReports():Flow<Resource<List<Report>>>
}