package com.example.finalprojectgg.ui.screens.mapdisaster.map.state

import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.ui.screens.ScreenState
import com.example.finalprojectgg.ui.screens.mapdisaster.map.MapStyle
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties

data class MapState(
    override var isProgress: Boolean = true,
    override var isError: Boolean = false,
    override var isContent: Boolean = false,
    override var isEmpty: Boolean = false,
    var isDetail:Boolean = false,
    var errorMessage:String = "",
    var reportModels: List<ReportModel> = listOf(),
    var properties: MapProperties = MapProperties(
        mapStyleOptions = MapStyleOptions(MapStyle.darkMapStyle),
    ),
): ScreenState