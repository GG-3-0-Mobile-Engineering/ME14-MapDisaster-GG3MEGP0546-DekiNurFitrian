package com.example.finalprojectgg.ui.screens.mapdisaster.map.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.sourceInformationMarkerEnd
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapEffect
import com.google.maps.android.compose.MapsComposeExperimentalApi
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import com.google.maps.android.ktx.model.markerOptions

@OptIn(MapsComposeExperimentalApi::class)
@Composable
fun MapView(
    modifier: Modifier = Modifier,
    mapState: MapState
) {
    val singapore = LatLng(-6.220680, 106.843445)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState()
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        properties = mapState.properties,
        cameraPositionState = cameraPositionState
    ) {
        LaunchedEffect(Unit) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(singapore, 11f)
        }
        mapState.reportModels.forEach {
            Marker(state = rememberMarkerState(position = it.latLng))
        }
    }

}

