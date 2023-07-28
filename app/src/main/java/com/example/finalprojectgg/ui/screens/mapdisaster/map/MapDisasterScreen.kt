package com.example.finalprojectgg.ui.screens.mapdisaster.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.domain.model.listDisaster
import com.example.finalprojectgg.ui.components.DisasterItem
import com.example.finalprojectgg.ui.components.FilterChipGroup
import com.example.finalprojectgg.ui.components.FullHeightBottomSheet
import com.example.finalprojectgg.ui.components.States
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapScreenEvent
import com.example.finalprojectgg.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapDisasterScreen(
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val mapScreenState by viewModel.mapScreenViewState.collectAsStateWithLifecycle()
        val reports by viewModel.filterdReport.collectAsStateWithLifecycle()

        LaunchedEffect(Unit){
            viewModel.onMapScreenEvent(MapScreenEvent.getReports)
        }

        Box(
            Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {
            val swipeableState = rememberSwipeableState(initialValue = States.PEEK)
            val scrollState = rememberLazyListState()

            viewModel.onMapScreenEvent(
                MapScreenEvent.BottomSheetChanged(
                    scrollState = scrollState,
                    swipeableState = swipeableState
                )
            )

            MapDisasterFilterChip()
            FullHeightBottomSheet(
                swipeableState = swipeableState,
                scrollState = scrollState
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .clip(MaterialTheme.shapes.small)
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                    ) {
                        Spacer(
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .height(4.dp)
                                .width(24.dp)
                                .clip(ShapeDefaults.ExtraLarge)
                                .background(MaterialTheme.colorScheme.outline)
                                .align(Alignment.TopCenter)
                        )
                        Column(
                            Modifier
                                .fillMaxSize()
                                .padding(horizontal = 24.dp),
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Info Bencana Terkini",
                                style = MaterialTheme.typography.titleLarge,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }

                items(reports.size) {
                    val item = reports[it]
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                            .padding(24.dp)
                    ) {
                        DisasterItem(item = item)
                    }
                }
            }
        }
    }
}


@Composable
fun MapDisasterFilterChip() {

    val selectedChipItem = remember {
        mutableStateListOf<ChipModel>()
    }

    FilterChipGroup(
        chipItems = listDisaster,
        selectedItem = selectedChipItem
    )

}