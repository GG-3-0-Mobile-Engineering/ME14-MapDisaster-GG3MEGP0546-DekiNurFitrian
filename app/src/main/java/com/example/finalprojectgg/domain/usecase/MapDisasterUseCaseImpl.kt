package com.example.finalprojectgg.domain.usecase

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.Report
import com.example.finalprojectgg.domain.model.listReportDummy
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MapDisasterUseCaseImpl @Inject constructor(private val repo: MapDisasterRepository) :
    MapDisasterUseCase {
    override fun getReports(): Flow<Resource<List<Report>>> = flow {
        emit(
            Resource.Success(
                listReportDummy
            )
        )
    }
}