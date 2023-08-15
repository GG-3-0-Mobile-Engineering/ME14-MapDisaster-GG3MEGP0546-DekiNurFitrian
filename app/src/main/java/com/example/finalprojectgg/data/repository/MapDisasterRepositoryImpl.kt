package com.example.finalprojectgg.data.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.data.source.NetworkBoundRepository
import com.example.finalprojectgg.data.source.local.LocalDataSource
import com.example.finalprojectgg.data.source.remote.RemoteDataSource
import com.example.finalprojectgg.data.source.remote.network.ApiResponse
import com.example.finalprojectgg.data.source.remote.response.GeometriesItem
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapDisasterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val coroutineScope: CoroutineScope
) : MapDisasterRepository {

    private val filterActive = mutableStateOf(FilterActive())
    private val filterActiveFlow =
        MutableSharedFlow<FilterActive>()

    override fun getReports(filterQuery: FilterActive): Flow<Resource<List<ReportModel>>> {
        return object : NetworkBoundRepository<List<ReportModel>, List<GeometriesItem?>>() {
            override suspend fun saveRemoteData(response: List<GeometriesItem?>) {
                localDataSource.insertReports(response.map {
                    Utils.DataMapper.reportApiToReportEntity(it)
                })
            }

            override fun fetchFromLocal(): Flow<List<ReportModel>> {
                return localDataSource.getAllReports(filterQuery).map { reportEntities ->
                    reportEntities.map { Utils.DataMapper.reportEntityToReportModel(it) }
                }
            }

            override suspend fun fetchFromRemote(): Flow<ApiResponse<List<GeometriesItem?>>> {
                return remoteDataSource.getReports()
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    override fun getReportsArchive(filterQuery: FilterActive): Flow<Resource<List<ReportModel>>> {
        return flow {
            emit(Resource.Loading())
            remoteDataSource.getReportsArchive(
                start = "2023-01-04T00%3A00%3A00%2B0700",
                end = "2023-02-04T00%3A00%3A00%2B0700"
            ).collect { apiResponse ->
                when (apiResponse) {
                    is ApiResponse.Success -> {
                        val listReport = apiResponse.data.map {
                            Utils.DataMapper.reportApiToReportModel(it)
                        }
                        val listReportFiltered = Utils.Filter.recentReport(filterQuery, listReport)
                        Log.d("FilteredDisasater", listReportFiltered.map { it.id }.toString())
                        emit(Resource.Success(listReportFiltered))
                    }

                    is ApiResponse.Empty -> emit(Resource.Empty())
                    is ApiResponse.Error -> emit(Resource.Error(apiResponse.error.message.toString()))
                }
            }
        }
    }

    override fun getFilterActive(): SharedFlow<FilterActive> = filterActiveFlow
    override fun getFilter(): StateFlow<FilterState> = MutableStateFlow(FilterState()).asStateFlow()
    override fun updateFilterActive(event: FilterEvent) {
        when (event) {
            is FilterEvent.OnDisasterChanged -> {
                val filterDisaster = filterActive.value.filterByDisaster
                if (filterDisaster.contains(event.disaster.id)) filterDisaster.remove(event.disaster.id) else filterDisaster.add(
                    event.disaster.id
                )
                filterActive.value.filterByDisaster = filterDisaster
                CoroutineScope(Dispatchers.IO).launch {
                    filterActiveFlow.emit(filterActive.value)
                }
            }

            is FilterEvent.OnProvinceChanged -> {
                val filterProvince = filterActive.value.filterByProvince
                if (filterProvince.contains(event.province.id)) filterProvince.remove(event.province.id) else filterProvince.add(
                    event.province.id
                )
                filterActive.value.filterByProvince = filterProvince
                CoroutineScope(Dispatchers.IO).launch {
                    filterActiveFlow.emit(filterActive.value)
                }
            }

            is FilterEvent.OnTimePeriodChanged -> {
                 filterActive.value.filterByTimePeriod = event.timePeriod
                CoroutineScope(Dispatchers.IO).launch {
                    filterActiveFlow.emit(filterActive.value)
                }
            }
        }
    }

    override fun getProvinceByQuery(query: String): Flow<List<FilterProvinceModel>> =
        localDataSource.getAllProvincesByQuery(query).map { data ->
            data.map {
                Utils.DataMapper.provinceEntityToProvinceModel(it)
            }
        }
}