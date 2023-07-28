package com.example.finalprojectgg.domain.repository

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.Report
import kotlinx.coroutines.flow.Flow

interface MapDisasterRepository {
    fun getReports(): Flow<Resource<List<Report>>>
}