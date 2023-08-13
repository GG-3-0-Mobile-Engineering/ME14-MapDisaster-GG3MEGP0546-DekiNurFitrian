package com.example.finalprojectgg.ui.screens.state

import com.example.finalprojectgg.domain.model.FilterDisasterModel
import com.example.finalprojectgg.domain.model.FilterProvinceModel

sealed class FilterEvent{
    data class OnDisasterChanged(val disaster:FilterDisasterModel):FilterEvent()
    data class OnTimePeriodChanged(val timePeriod: TimePeriod?):FilterEvent()
    data class OnProvinceChanged(val province:FilterProvinceModel):FilterEvent()
}
