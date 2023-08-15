package com.example.finalprojectgg.data.source.local

import com.example.finalprojectgg.data.source.local.entity.ProvinceEntity
import com.example.finalprojectgg.data.source.local.entity.ReportEntity
import com.example.finalprojectgg.data.source.local.room.DisasterDao
import com.example.finalprojectgg.data.source.local.room.ProvinceDao
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.ui.screens.state.FilterState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val disasterDao: DisasterDao,
    private val provinceDao: ProvinceDao
) {
    /**
     * Get Reports Data from Room Database
     */
    fun getAllReports(
        filterQuery: FilterActive
    ) = disasterDao.getAllReports(
        useFilterDisaster = filterQuery.filterByDisaster.isNotEmpty(),
        filterDisaster = filterQuery.filterByDisaster,
        useFilterProvince = filterQuery.filterByProvince.isNotEmpty(),
        filterProvince = filterQuery.filterByProvince
    )
    /**
     * Insert Reports Data from Room Database
     */
    suspend fun insertReports(reports: List<ReportEntity>) = disasterDao.insertReports(reports)

    /**
     * Get all Province Data from Room Database
     */
    fun getAllProvinces():Flow<List<ProvinceEntity>> = provinceDao.getAllProvince()

    /**
     * Get all Province Data with given queries Room Database
     */
    fun getAllProvincesByQuery(query:String):Flow<List<ProvinceEntity>> = provinceDao.getProvincesByName("%$query%")
}