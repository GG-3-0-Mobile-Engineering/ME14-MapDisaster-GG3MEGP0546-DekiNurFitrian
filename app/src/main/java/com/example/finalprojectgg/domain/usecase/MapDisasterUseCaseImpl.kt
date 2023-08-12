package com.example.finalprojectgg.domain.usecase

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.domain.repository.MapDisasterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
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

    override fun getReports(): Flow<Resource<List<ReportModel>>> = repo.getReports()

}