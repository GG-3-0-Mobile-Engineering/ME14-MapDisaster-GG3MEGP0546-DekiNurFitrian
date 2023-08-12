package com.example.finalprojectgg.data.repository

import androidx.compose.runtime.mutableStateOf
import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.data.source.NetworkBoundRepository
import com.example.finalprojectgg.data.source.local.LocalDataSource
import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import com.example.finalprojectgg.data.source.remote.RemoteDataSource
import com.example.finalprojectgg.data.source.remote.network.ApiResponse
import com.example.finalprojectgg.data.source.remote.response.GeometriesItem
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MapDisasterRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : MapDisasterRepository {

    private val filterActive = mutableStateOf(FilterActive())
    private val _filterActiveFlow =
        MutableSharedFlow<FilterActive>(replay = 1, extraBufferCapacity = 1)

    override fun getReports(): Flow<Resource<List<ReportModel>>> {
        return object : NetworkBoundRepository<List<ReportModel>, List<GeometriesItem?>>() {
            override suspend fun saveRemoteData(response: List<GeometriesItem?>) {
                localDataSource.insertReports(response.map {
                    Utils.DataMapper.reportApiToReportEntity(it)
                })
            }

            override fun fetchFromLocal(): Flow<List<ReportModel>> {
                return localDataSource.getAllReports().map { reportEntities ->
                    reportEntities.map { Utils.DataMapper.reportEntityToReportModel(it) }
                }
            }

            override suspend fun fetchFromRemote(): Flow<ApiResponse<List<GeometriesItem?>>> {
                return remoteDataSource.getReports()
            }

        }.asFlow().flowOn(Dispatchers.IO)
    }

    override fun getFilterActive(): SharedFlow<FilterActive> = _filterActiveFlow
    override fun getFilter(): MutableStateFlow<FilterState> = MutableStateFlow(FilterState())
    override fun updateFilterActive(event: FilterEvent) {
        when (event) {
            is FilterEvent.OnDisasterChanged -> {
                val filterDisaster = filterActive.value.filterByDisaster
                if (filterDisaster.contains(event.disaster.title)) filterDisaster.remove(event.disaster.title) else filterDisaster.add(
                    event.disaster.title
                )
                CoroutineScope(Dispatchers.IO).launch {
                    _filterActiveFlow.emit(filterActive.value)
                }
            }

            is FilterEvent.OnProvinceChanged -> {

            }

            is FilterEvent.OnTimePeriodChanged -> {

            }
        }
    }

}