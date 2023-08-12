package com.example.finalprojectgg.data.source

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResourceQualifier<ResultType, RequestType> {
    private var result: Flow<Resource<ResultType?>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }

                is ApiResponse.Empty -> {
                    emitAll(loadFromDB().map { Resource.Success(it) })
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Resource.Error(apiResponse.error.toString()))
                }
            }
        } else {
            emitAll(loadFromDB().map { Resource.Success(it) })
        }
    }.flowOn(Dispatchers.IO)

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType?>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType?>> = result
}

abstract class NetworkBoundRepository<RESULT, REQUEST> {

    fun asFlow() = flow<Resource<RESULT>> {

        // Emit Loading State
        emit(Resource.Loading())

        try {
            // Emit Database content first
            emit(Resource.Success(fetchFromLocal().first()))

            // Fetch latest posts from remote
            when (val apiResponse = fetchFromRemote().first()) {
                is ApiResponse.Success -> {
                    saveRemoteData(apiResponse.data)
                    emitAll(fetchFromLocal().map { Resource.Success(it) })
                }

                is ApiResponse.Empty -> {
                    emitAll(fetchFromLocal().map { Resource.Success(it) })
                }

                is ApiResponse.Error -> {
                    emit(Resource.Error(apiResponse.error.toString()))
                }
            }
        } catch (e: Exception) {
            // Exception occurred! Emit error
            emit(Resource.Error("Network error! Can't get latest data."))
            e.printStackTrace()
        }

        // Retrieve posts from persistence storage and emit
        emitAll(fetchFromLocal().map {
            Resource.Success<RESULT>(it)
        })
    }

    /**
     * Saves retrieved from remote into the persistence storage.
     */
    @WorkerThread
    protected abstract suspend fun saveRemoteData(response: REQUEST)

    /**
     * Retrieves all data from persistence storage.
     */
    @MainThread
    protected abstract fun fetchFromLocal(): Flow<RESULT>

    /**
     * Fetches [Response] from the remote end point.
     */
    @MainThread
    protected abstract suspend fun fetchFromRemote(): Flow<ApiResponse<REQUEST>>
}