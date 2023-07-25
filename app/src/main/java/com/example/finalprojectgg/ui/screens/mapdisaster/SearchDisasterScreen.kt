package com.example.finalprojectgg.ui.screens.mapdisaster

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.ui.components.FilterChipGroup
import com.example.finalprojectgg.ui.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchDisasterScreen(
    viewModel: MainViewModel
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        //Chip
        val sheetStateFilter =
            rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
        val scope = rememberCoroutineScope()
        var isTransitionAnimation by remember { mutableStateOf(true) }
        val searchDisasterScreenState = viewModel.searchDisasterScreenState

        LaunchedEffect(key1 = this) {
            launch {
                isTransitionAnimation = true
                delay(500)
                isTransitionAnimation = false
            }
            searchDisasterScreenState.collectLatest {
                it.filterClicked = {
                    scope.launch {
                        sheetStateFilter.show()
                    }
                }
            }
        }



        ModalBottomSheetLayout(
            sheetState = sheetStateFilter,
            sheetContent = {
                if (!isTransitionAnimation) {
                    FilterSheet()
                }
            }) {

        }
    }
}

@Composable
fun FilterSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.Red)
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun FilterSearchDisasterSheet() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val selectedChipItem = remember {
            mutableStateListOf<ChipModel>()
        }
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
        Text(
            text = "Filter :",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold)
        )
        FilterChipGroup(chipItems = filterBencana, selectedItem = selectedChipItem)
    }
}