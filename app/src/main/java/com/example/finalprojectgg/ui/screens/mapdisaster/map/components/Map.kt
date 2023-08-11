package com.example.finalprojectgg.ui.screens.mapdisaster.map.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapState
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    mapState: MapState
) {
    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState: CameraPositionState = rememberCameraPositionState()
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        properties =mapState.properties,
        cameraPositionState = cameraPositionState
    ){
        LaunchedEffect(Unit){
            cameraPositionState.position = CameraPosition.fromLatLngZoom(singapore,11f)
        }
        Marker(position = singapore, title = "Singapore", snippet = "Snippet", onInfoWindowClose = {
            Log.d("MARKER","Closed")
        })
    }
}