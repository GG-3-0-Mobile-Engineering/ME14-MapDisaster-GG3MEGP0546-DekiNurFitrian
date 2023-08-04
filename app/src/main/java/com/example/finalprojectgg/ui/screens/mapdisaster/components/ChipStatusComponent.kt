package com.example.finalprojectgg.ui.screens.mapdisaster.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun ChipStatus(
    modifier: Modifier = Modifier,
    text: String = "Banjir",
    color: Color = MaterialTheme.colorScheme.errorContainer
) {
    Row(
        modifier = modifier
            .height(24.dp)
            .clip(MaterialTheme.shapes.small)
            .background(color)
            .padding(horizontal = 8.dp)
        , verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = text, style = MaterialTheme.typography.labelSmall)
    }
}