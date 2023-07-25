package com.example.finalprojectgg.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
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

import com.example.finalprojectgg.domain.model.ChipModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterChipGroup(
    chipItems: List<ChipModel>,
    selectedItem: SnapshotStateList<ChipModel>
) {
    LazyRow(
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        items(chipItems.size) { index: Int ->
            val item = chipItems[index]
            var selected by remember {
                mutableStateOf(false)
            }

            FilterChip(
                selected = selected,
                onClick = {
                    selected = when (selected) {
                        true -> {
                            selectedItem.remove(item)
                            false
                        }

                        false -> {
                            selectedItem.add(item)
                            true
                        }
                    }
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