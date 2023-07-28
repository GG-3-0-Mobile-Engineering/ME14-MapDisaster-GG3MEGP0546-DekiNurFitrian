package com.example.finalprojectgg.data.source.remote

import android.util.Log
import com.example.finalprojectgg.data.source.remote.network.ApiResponse
import com.example.finalprojectgg.data.source.remote.network.PetaBencanaApiService
import com.example.finalprojectgg.data.source.remote.response.GeometriesItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: PetaBencanaApiService) {
    suspend fun getReports(): Flow<ApiResponse<List<GeometriesItem?>>> {
        return flow {
            try {
                val response = apiService.getReports()
                val dataArray = response.result?.objects?.output?.geometries

                dataArray?.let {
                    if (dataArray.isNotEmpty()) {
                        emit(ApiResponse.Success(response.result.objects.output.geometries))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e: HttpException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", e.toString())
            } catch (e: IOException) {
                emit(ApiResponse.Error(e))
                Log.e("Remote Data Source", "Check Connection")
            }
        }.flowOn(Dispatchers.IO)
    }
}
