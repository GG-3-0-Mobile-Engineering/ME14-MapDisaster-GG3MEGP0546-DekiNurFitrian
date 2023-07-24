package com.example.finalprojectgg.ui.screens.mapdisaster

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun SearchDisasterScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Spacer(
            modifier = Modifier
                .graphicsLayer {
                    translationY = 1000f
                    translationX = 200f
                }
                .size(50.dp)
                .background(color = MaterialTheme.colorScheme.primary)

        )
    }
}