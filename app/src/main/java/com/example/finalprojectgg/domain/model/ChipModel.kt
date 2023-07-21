package com.example.finalprojectgg.domain.model

import androidx.compose.runtime.Composable

data class ChipModel(
    val title: String,
    val icon: @Composable () -> Unit
)
