package com.example.finalprojectgg.ui.screens.state

import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.domain.model.FilterProvinceModel

sealed class FilterEvent{
    data class OnDisasterChanged(val disaster:ChipModel):FilterEvent()
    data class OnTimePeriodChanged(val timePeriod: TimePeriod):FilterEvent()
    data class OnProvinceChanged(val province:FilterProvinceModel):FilterEvent()
}
