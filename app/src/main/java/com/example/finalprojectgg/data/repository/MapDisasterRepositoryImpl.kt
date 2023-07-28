package com.example.finalprojectgg.data.repository

import android.util.Log
import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.data.source.remote.RemoteDataSource
import com.example.finalprojectgg.data.source.remote.network.ApiResponse
import com.example.finalprojectgg.domain.model.Report
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapDisasterRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    MapDisasterRepository {
    override fun getReports(): Flow<Resource<List<Report>>> {
        return flow{
            emit(Resource.Loading())
            remoteDataSource.getReports().collect{apiResponse ->
                when (apiResponse){
                    is ApiResponse.Success -> {
                       Log.d("Remote","Success Connect")
                       Log.d("Remote",apiResponse.data.size.toString())
                    }
                    is ApiResponse.Empty -> {

                    }
                    is ApiResponse.Error -> {

                    }
                }
            }
        }
    }
}