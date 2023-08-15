package com.example.finalprojectgg.ui.screens.mapdisaster.map

import android.Manifest
import android.os.Build
import android.util.Log
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.SwipeableState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
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
import com.example.finalprojectgg.domain.model.FilterDisasterModel
import com.example.finalprojectgg.ui.components.EmptyContentView
import com.example.finalprojectgg.ui.screens.mapdisaster.components.DisasterItem
import com.example.finalprojectgg.ui.components.FullHeightBottomSheet
import com.example.finalprojectgg.ui.components.LoadingReportView
import com.example.finalprojectgg.ui.components.States
import com.example.finalprojectgg.ui.components.Test2FilterChipGroup
import com.example.finalprojectgg.ui.components.shimmerEffect
import com.example.finalprojectgg.ui.screens.main.components.RequestMultiplePermissionsView
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapScreenEvent
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapState
import com.example.finalprojectgg.ui.screens.profile.componentes.InformationDialogView
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.ui.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun MapDisasterScreen(
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    val swipeableState = rememberSwipeableState(initialValue = States.PEEK)
    val scrollState = rememberLazyListState()
    val mapState by viewModel.mapScreenViewState.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        listOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    } else {
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    }
    val multiplePermissionsState = rememberMultiplePermissionsState(permissions)
    val permissionGranted = multiplePermissionsState.permissions.all {
        it.status == PermissionStatus.Granted
    }

    LaunchedEffect(Unit) {
        viewModel.onFilterEvent(FilterEvent.OnInit)
        if (!permissionGranted) {
            multiplePermissionsState.launchMultiplePermissionRequest()
        }
    }

    RequestMultiplePermissionsView(multiplePermissionsState)

    viewModel.onMapScreenEvent(
        MapScreenEvent.BottomSheetChanged(
            scrollState = scrollState,
            swipeableState = swipeableState
        )
    )
    MapDisasterScreenView(
        mapState = mapState,
        filterState = filterState,
        paddingValues = paddingValues,
        swipeableState = swipeableState,
        scrollState = scrollState,
        onFilterChipClick = {
            viewModel.onFilterEvent(FilterEvent.OnDisasterChanged(it))
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MapDisasterScreenView(
    mapState: MapState,
    filterState: FilterState,
    paddingValues: PaddingValues,
    swipeableState: SwipeableState<States>,
    scrollState: LazyListState,
    onFilterChipClick: (FilterDisasterModel) -> Unit,
) {
    var errorDialogState by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(mapState.isError) {
        errorDialogState = mapState.isError

    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding())
        ) {

            MapDisasterFilterChip(filterState.disasterFilter, onFilterChipClick)

            FullHeightBottomSheet(
                swipeableState = swipeableState,
                scrollState = scrollState
            ) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
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
                        if (mapState.isProgress) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(6.dp)
                                    .shimmerEffect()
                                    .align(
                                        Alignment.BottomCenter
                                    )
                            )
                        }
                    }
                }
                if (mapState.isProgress) {
                    items(3) {
                        LoadingReportView()
                    }
                }

                if (mapState.reportModels.isEmpty() && !mapState.isProgress) {
                    Log.d("report", "Empty")
                    item {
                        EmptyContentView()
                    }
                }

                items(items = mapState.reportModels, key = { it.id }) { item ->
                    Box {
                        DisasterItem(item = item)
                    }
                }
            }
        }

        ErrorView(
            errorDesc = mapState.errorMessage,
            errorDialogState = errorDialogState,
            onDismiss = {
                errorDialogState = false
            }
        )
    }

}

@Composable
fun ErrorView(
    errorDesc: String,
    errorDialogState: Boolean,
    onDismiss: () -> Unit
) {
    InformationDialogView(
        title = "Error",
        icon = {
            Icon(imageVector = Icons.Filled.ErrorOutline, contentDescription = "Error")
        },
        text = errorDesc,
        informationDialogState = errorDialogState,
        confirmButton = { },
        onDismiss = onDismiss
    )
}


@Composable
fun MapDisasterFilterChip(
    chipState: List<FilterDisasterModel>,
    onItemChipClick: (FilterDisasterModel) -> Unit,
) {
    Test2FilterChipGroup(chipState = chipState, onItemChipClick = onItemChipClick)
}