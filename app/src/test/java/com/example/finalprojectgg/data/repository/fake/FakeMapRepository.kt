package com.example.finalprojectgg.data.repository.fake

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.domain.model.listProvince
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.util.listReportsDummy
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flowOf

class FakeMapRepository:MapDisasterRepository {

    /**
     * Hot flow only for test filterActive.
     */
    private val filterActive= MutableStateFlow(FilterActive())
    /**
     * Hot flow only for test reports.
     */
    private val reports: MutableSharedFlow<Resource<List<ReportModel>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    /**
     * Hot flow only for test reports.
     */
    private val reportsArchive: MutableSharedFlow<Resource<List<ReportModel>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    /**
     * Hot flow only for test provinceByQuery.
     */
    private val provinceByQuery: MutableSharedFlow<List<FilterProvinceModel>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override fun getReports(filterQuery: FilterActive): Flow<Resource<List<ReportModel>>> = reports

    override fun getReportsArchive(filterQuery: FilterActive): Flow<Resource<List<ReportModel>>> = reportsArchive

    override fun getFilterActive(): MutableStateFlow<FilterActive> = filterActive

    override fun getFilter(): MutableStateFlow<FilterState> = MutableStateFlow(FilterState())

    override fun updateFilterActive(event: FilterEvent) {
        throw NotImplementedError("Unused in tests")
    }

    override fun getProvinceByQuery(query: String): Flow<List<FilterProvinceModel>> = provinceByQuery
}