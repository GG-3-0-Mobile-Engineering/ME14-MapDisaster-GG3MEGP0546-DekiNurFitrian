package com.example.finalprojectgg.data.source

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import okhttp3.Dispatcher

abstract class NetworkResourceBounds<ResultType, RequestType> {
//    private val result: Flow<Resource<ResultType>> = flow<Resource<ResultType>> {
//        emit(Resource.Loading())
//        val dbSource = loadFromDB().first()
//        if (shouldFetch(dbSource)) {
//            emit(Resource.Loading())
//            when (val apiResponse = createCall().first()) {
//                is ApiResponse.Success -> {
//                    saveCallResult(apiResponse.data)
//                    emitAll(loadFromDB().map { Resource.Success(it) })
//                }
//                is ApiResponse.Empty -> {
//                    emitAll(loadFromDB().map { Resource.Success(it) })
//                }
//                is ApiResponse.Error -> {
//                    onFetchFailed()
//                    emit(Resource.Error(apiResponse.error.toString()))
//                }
//            }
//        } else {
//            emitAll(loadFromDB().map { Resource.Success(it) })
//        }
//    }.flowOn(Dispatchers.IO)
//
//    protected open fun onFetchFailed() {}
//
//    protected abstract fun loadFromDB(): Flow<ResultType?>
//
//    protected abstract fun shouldFetch(data: ResultType?): Boolean
//
//    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
//
//    protected abstract suspend fun saveCallResult(data: RequestType)
//
//    fun asFlow(): Flow<Resource<ResultType>> = result
}