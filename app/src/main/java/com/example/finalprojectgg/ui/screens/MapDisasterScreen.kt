package com.example.finalprojectgg.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.ui.components.FilterChipGroup
import com.example.finalprojectgg.ui.navigation.Screens

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapDisasterScreen() {
    val bottomSheetState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    BottomSheetScaffold(
        scaffoldState = bottomSheetState,
        sheetContent = {
            MapDisasterBottomSheet()
        },
        sheetShape = MaterialTheme.shapes.medium
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary), contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)) {
                MapDisasterSearch(
                    modifier = Modifier
                        .padding(10.dp)
                )
                MapdisasterFilterChip()
            }
            Text(text = "INI ADALAH MAP", color = MaterialTheme.colorScheme.surface)
        }
    }
}

@Composable
fun MapDisasterBottomSheet() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Text(text = "Bottom Sheet")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapDisasterSearch(modifier: Modifier = Modifier) {
    Row(
        modifier
            .fillMaxWidth()
            .clip(ShapeDefaults.ExtraLarge)
            .background(Color.Transparent)
    ) {
        TextField(
            value = "text",
            onValueChange = { },
            label = { Text("Search") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MapdisasterFilterChip(modifier: Modifier = Modifier) {

    val filterBencana = arrayListOf(
        ChipModel(
            title = "Banjir",
            icon = {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
            }
        ),
        ChipModel(
            title = "Banjir",
            icon = {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
            }
        ),
        ChipModel(
            title = "Banjir",
            icon = {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
            }
        ),
        ChipModel(
            title = "Banjir",
            icon = {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
            }
        ),
        ChipModel(
            title = "Banjir",
            icon = {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
            }
        ),
        ChipModel(
            title = "Banjir",
            icon = {
                Icon(imageVector = Icons.Default.Build, contentDescription = null)
            }
        ),
    )

    val selectedChipItem = remember {
        mutableStateListOf<ChipModel>()
    }


    FilterChipGroup(
        chipItems = filterBencana,
        selectedItem = selectedChipItem
    )

}