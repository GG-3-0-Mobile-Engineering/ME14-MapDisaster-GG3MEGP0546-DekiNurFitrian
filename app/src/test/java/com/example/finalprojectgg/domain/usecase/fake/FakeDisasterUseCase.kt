package com.example.finalprojectgg.domain.usecase.fake

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.domain.usecase.MapDisasterUseCase
import com.example.finalprojectgg.ui.screens.state.FilterState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeDisasterUseCase : MapDisasterUseCase {

    /**
     * Hot flow only for test report.
     */
    val reports: MutableSharedFlow<Resource<List<ReportModel>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    /**
     * Hot flow only for test filter active.
     */
    val filterActive: MutableSharedFlow<FilterState> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)


    override fun getReports(): Flow<Resource<List<ReportModel>>> = reports

    override fun getFilterActive(): Flow<FilterState> = filterActive

}