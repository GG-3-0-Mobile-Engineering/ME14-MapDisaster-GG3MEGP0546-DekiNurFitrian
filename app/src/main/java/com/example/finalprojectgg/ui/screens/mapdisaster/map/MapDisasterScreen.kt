package com.example.finalprojectgg.ui.screens.mapdisaster.map

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.finalprojectgg.domain.model.FilterDisasterModel
import com.example.finalprojectgg.ui.screens.mapdisaster.components.DisasterItem
import com.example.finalprojectgg.ui.components.FullHeightBottomSheet
import com.example.finalprojectgg.ui.components.LoadingReportView
import com.example.finalprojectgg.ui.components.States
import com.example.finalprojectgg.ui.components.Test2FilterChipGroup
import com.example.finalprojectgg.ui.components.shimmerEffect
import com.example.finalprojectgg.ui.screens.main.components.RequestMultiplePermissions
import com.example.finalprojectgg.ui.screens.main.components.RequestMultiplePermissionsView
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapScreenEvent
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.viewmodel.MainViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.shouldShowRationale

@OptIn(ExperimentalMaterialApi::class, ExperimentalPermissionsApi::class)
@Composable
fun MapDisasterScreen(
    paddingValues: PaddingValues,
    viewModel: MainViewModel
) {
    val mapState by viewModel.mapScreenViewState.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()
    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
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
    RequestMultiplePermissionsView(multiplePermissionsState)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LaunchedEffect(Unit) {
            viewModel.onMapScreenEvent(MapScreenEvent.GetReport)
            if (!permissionGranted){
                multiplePermissionsState.launchMultiplePermissionRequest()
            }
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

                if (mapState.isEmpty) {
                    item {
                        //TODO: Lootie anim empty
                    }
                }

                items(items = mapState.reportModels, key = { it.id }) { item ->
                    Box(

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
    chipState: List<FilterDisasterModel>,
    onItemChipClick: (FilterDisasterModel) -> Unit,
) {
    Test2FilterChipGroup(chipState = chipState, onItemChipClick = onItemChipClick)
}