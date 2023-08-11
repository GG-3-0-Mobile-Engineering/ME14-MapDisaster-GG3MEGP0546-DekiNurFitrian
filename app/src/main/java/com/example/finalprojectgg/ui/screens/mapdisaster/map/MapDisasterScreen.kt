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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.domain.model.listReportDummy
import com.example.finalprojectgg.ui.screens.mapdisaster.components.DisasterItem
import com.example.finalprojectgg.ui.components.FullHeightBottomSheet
import com.example.finalprojectgg.ui.components.States
import com.example.finalprojectgg.ui.components.Test2FilterChipGroup
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapScreenEvent
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapDisasterScreen(
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    val mapState by viewModel.mapScreenViewState.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LaunchedEffect(Unit) {
            viewModel.onMapScreenEvent(MapScreenEvent.GetReport)
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

            MapDisasterFilterChip(filterState.disasterFilter) {
                viewModel.onFilterEvent(FilterEvent.OnDisasterChanged(it))
            }

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
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                items(items = mapState.reports, key = { it.id }) {item ->
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                    ) {
                        DisasterItem(item = item)
                    }
                }
            }
        }
    }
}


@Composable
fun MapDisasterFilterChip(
    chipState: List<ChipModel>,
    onItemChipClick: (ChipModel) -> Unit,
) {
    Test2FilterChipGroup(chipState = chipState, onItemChipClick = onItemChipClick)
}
