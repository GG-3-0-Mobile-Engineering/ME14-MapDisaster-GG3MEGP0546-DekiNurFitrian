package com.example.finalprojectgg.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.ui.screens.state.FilterState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SheetLocationPicker(
    sheetState: ModalBottomSheetState,
    filterState: FilterState,
    onItemClick: (FilterProvinceModel) -> Unit
) {
    ModalBottomSheetLayout(
        sheetContent = {
            SheetContentLocationPicker(filterState.provinceFilter, onItemClick)
        },
        sheetState = sheetState,
        sheetBackgroundColor = MaterialTheme.colorScheme.surface,
        sheetContentColor = MaterialTheme.colorScheme.onSurface
    ) {

    }

}

@Composable
fun SheetContentLocationPicker(
    filterProvince: List<FilterProvinceModel>,
    onItemClick: (FilterProvinceModel) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(
            modifier = Modifier
                .height(4.dp)
                .width(24.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(MaterialTheme.colorScheme.outline)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = "Search Location",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(items = filterProvince, key = { it.id }) { item ->
                ItemLocationPicker(province = item, onItemClick = onItemClick)
            }
        }
    }
}

@Composable
fun ItemLocationPicker(
    province: FilterProvinceModel,
    onItemClick: (FilterProvinceModel) -> Unit
) {
    Column() {
        Row(
            Modifier
                .fillMaxWidth()
                .height(40.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = province.name, style = MaterialTheme.typography.bodySmall)
            Checkbox(checked = province.isActive, onCheckedChange = { onItemClick(province) })
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(MaterialTheme.colorScheme.outline)
        )
    }
}