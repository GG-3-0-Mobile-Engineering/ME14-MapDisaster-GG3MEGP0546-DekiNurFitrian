package com.example.finalprojectgg.domain.usecase

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.Report
import com.example.finalprojectgg.domain.model.listReportDummy
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.ui.screens.state.TimePeriod
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class MapDisasterUseCaseImpl @Inject constructor(
    private val repo: MapDisasterRepository
) :
    MapDisasterUseCase {

    fun getFilterActive() = combine(
        repo.getFilter(),
        repo.getFilterActive()
    ) { filterState, filterActive ->
        val disasterFilter = filterState.disasterFilter.map {
            it.copy(selected = filterActive.filterByDisaster.contains(it.title))
        }
        val provinceFilter = filterState.provinceFilter.map{
            it.copy(isActive = filterActive.filterByProvince.contains(it.id))
        }
        val timePeriodFilter = filterActive.filterByTimePeriod
        filterState.copy(disasterFilter = disasterFilter,provinceFilter = provinceFilter, timePeriodFilter = timePeriodFilter)
    }

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

    fun getReportByFilter(filter: String): Flow<Resource<List<Report>>> = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(listReportDummy.filter {
                it.category == filter
            }))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.toString()))
        }
    }
}