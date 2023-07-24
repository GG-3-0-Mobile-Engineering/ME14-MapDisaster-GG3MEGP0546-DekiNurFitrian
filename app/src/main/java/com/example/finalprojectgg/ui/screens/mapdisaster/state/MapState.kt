package com.example.finalprojectgg.ui.screens.mapdisaster.state

import androidx.compose.ui.res.stringResource
import com.example.finalprojectgg.R
import com.example.finalprojectgg.ui.screens.mapdisaster.MapStyle
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.maps.android.compose.MapProperties

data class MapState(
    val properties: MapProperties = MapProperties(
        mapStyleOptions = MapStyleOptions(MapStyle.darkMapStyle),
    )
)
