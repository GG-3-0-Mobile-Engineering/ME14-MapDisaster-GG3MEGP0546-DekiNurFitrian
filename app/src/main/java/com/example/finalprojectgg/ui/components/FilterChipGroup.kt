package com.example.finalprojectgg.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.domain.model.listDisaster
import com.example.finalprojectgg.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChipGroup(
    chipState: List<ChipModel>,
    viewModel: MainViewModel = hiltViewModel()
) {
    LazyRow(
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(items = chipState, key = { index, item -> item.title }) { index, item ->
            FilterChip(
                selected = item.selected,
                onClick = {
//                    viewModel.update(index)
                },
                selectedIcon = {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null
                    )
                },
                colors = ChipDefaults.filterChipColors(
                    selectedBackgroundColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    leadingIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = item.title, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TestFilterChipGroup(
    chipState: List<ChipModel>,
    onItemChipClick: (Int) -> Unit
) {
    LazyRow(
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(items = chipState, key = { index, item -> item.title }) { index, item ->
            FilterChip(
                selected = item.selected,
                onClick = { onItemChipClick(index) },
                selectedIcon = {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null
                    )
                },
                colors = ChipDefaults.filterChipColors(
                    selectedBackgroundColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    leadingIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = item.title, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Test2FilterChipGroup(
    chipState: List<ChipModel>,
    onItemChipClick: (ChipModel) -> Unit
) {
    LazyRow(
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        itemsIndexed(items = chipState, key = { index, item -> item.title }) { index, item ->
            FilterChip(
                selected = chipState.contains(item),
                onClick = { onItemChipClick(item) },
                selectedIcon = {
                    Icon(
                        imageVector = Icons.Default.Build,
                        contentDescription = null
                    )
                },
                colors = ChipDefaults.filterChipColors(
                    selectedBackgroundColor = MaterialTheme.colorScheme.primary,
                    backgroundColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                    selectedContentColor = MaterialTheme.colorScheme.onPrimary,
                    leadingIconColor = MaterialTheme.colorScheme.onSurface,
                    selectedLeadingIconColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(text = item.title, style = MaterialTheme.typography.labelMedium)
            }
        }
    }
}