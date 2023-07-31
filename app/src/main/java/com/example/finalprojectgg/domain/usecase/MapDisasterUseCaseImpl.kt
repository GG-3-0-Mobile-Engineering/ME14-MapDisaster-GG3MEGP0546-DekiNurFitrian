package com.example.finalprojectgg.domain.usecase

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.Report
import com.example.finalprojectgg.domain.model.listReportDummy
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MapDisasterUseCaseImpl @Inject constructor(private val repo: MapDisasterRepository) :
    MapDisasterUseCase {
    override fun getReports(): Flow<Resource<List<Report>>> = flow {
        emit(Resource.Loading())
        delay(2000)
        emit(
            Resource.Success(
                listReportDummy
            )
        )
        delay(500)
        emit(Resource.Error(""))
    }

    fun getReportByFilter(filter:String):Flow<Resource<List<Report>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(listReportDummy.filter {
                it.category == filter
            }))
        } catch (e:Exception){
            emit(Resource.Error(message = e.toString()))
        }
    }
}