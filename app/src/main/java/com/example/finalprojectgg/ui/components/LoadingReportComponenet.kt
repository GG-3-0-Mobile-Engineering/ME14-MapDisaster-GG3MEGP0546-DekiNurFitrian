package com.example.finalprojectgg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.finalprojectgg.R
import com.example.finalprojectgg.ui.screens.mapdisaster.components.ChipStatus

@Preview(showBackground = true)
@Composable
fun LoadingReportView() {
    Column(
        Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(ShapeDefaults.Small)
                .shimmerEffect()
        )
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                Modifier
                    .height(16.dp)
                    .width(96.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .shimmerEffect()
            )

            Box(
                Modifier
                    .height(16.dp)
                    .width(48.dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .shimmerEffect()
            )
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            repeat(3){
                Box(
                    Modifier
                        .height(16.dp)
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.extraSmall)
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Box(
            Modifier
                .padding(horizontal = 8.dp)
                .padding(bottom = 8.dp)
                .height(16.dp)
                .width(96.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .shimmerEffect()
        )
    }
}