package com.example.finalprojectgg.ui.screens.mapdisaster

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
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
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
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.ui.components.DisasterItem
import com.example.finalprojectgg.ui.components.FilterChipGroup
import com.example.finalprojectgg.ui.components.FullHeightBottomSheet
import com.example.finalprojectgg.ui.components.States
import com.example.finalprojectgg.ui.screens.mapdisaster.components.MapView
import com.example.finalprojectgg.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapDisasterScreen(
    paddingValues: PaddingValues,
    viewModel: MainViewModel,
    navToMapDisasterSearchScreen: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        MapView(mapState = viewModel.mapState)

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
                items(10) {
                    if (it == 0) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                                .padding(top = 0.dp, start = 24.dp, end = 24.dp, bottom = 24.dp)
                        ) {
                            Column(
                                Modifier
                                    .fillMaxWidth()
                                    .height(50.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Spacer(
                                    modifier = Modifier
                                        .height(4.dp)
                                        .width(24.dp)
                                        .clip(ShapeDefaults.ExtraLarge)
                                        .background(MaterialTheme.colorScheme.outline)
                                )
                                Text(
                                    text = "Info Bencana Terkini",
                                    style = MaterialTheme.typography.titleLarge,
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            DisasterItem()
                        }
                    } else {
                        DisasterItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(MaterialTheme.shapes.small)
                                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.2f))
                                .padding(24.dp),
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun MapDisasterFilterChip(modifier: Modifier = Modifier) {
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