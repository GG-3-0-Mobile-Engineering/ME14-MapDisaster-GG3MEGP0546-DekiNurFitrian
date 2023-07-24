package com.example.finalprojectgg.ui.screens.mapdisaster.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.finalprojectgg.ui.screens.mapdisaster.state.MapState
import com.google.maps.android.compose.GoogleMap

@Composable
fun MapView(
    modifier: Modifier = Modifier,
    mapState: MapState
) {
    GoogleMap(modifier = modifier.fillMaxSize(), properties = mapState.properties)
}