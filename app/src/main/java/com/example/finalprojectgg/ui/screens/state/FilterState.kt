package com.example.finalprojectgg.ui.screens.state

import android.os.Build
import android.util.Range
import androidx.annotation.RequiresApi
import com.example.finalprojectgg.domain.model.FilterDisasterModel
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.listDisaster
import com.example.finalprojectgg.domain.model.listProvince
import java.time.LocalDate

data class FilterState(
    var disasterFilter: List<FilterDisasterModel> = listDisaster,
    var timePeriodFilter:TimePeriod? = null,
    var provinceFilter: List<FilterProvinceModel> = listProvince
)

data class TimePeriod(
    var startTime:LocalDate,
    var endTime:LocalDate
){
    @RequiresApi(Build.VERSION_CODES.O)
    fun getRangeTime():Range<LocalDate>{
        return Range(startTime,endTime)
    }
}


