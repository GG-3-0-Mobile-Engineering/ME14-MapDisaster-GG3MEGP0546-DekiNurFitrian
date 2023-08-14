package com.example.finalprojectgg.ui.screens.mapdisaster.search

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.finalprojectgg.domain.model.FilterDisasterModel
import com.example.finalprojectgg.domain.model.FilterProvinceModel
import com.example.finalprojectgg.domain.model.ReportModel
import com.example.finalprojectgg.domain.model.listProvince
import com.example.finalprojectgg.ui.components.FlowRow
import com.example.finalprojectgg.ui.components.SheetLocationPicker
import com.example.finalprojectgg.ui.screens.mapdisaster.components.DisasterItem
import com.example.finalprojectgg.ui.screens.mapdisaster.components.SearchLocationComponent
import com.example.finalprojectgg.ui.screens.state.FilterEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.ui.screens.state.TimePeriod
import com.example.finalprojectgg.ui.viewmodel.MainViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchDisasterScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    val sheetStateFilter =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val sheetStateLocationPicker =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    var isTransitionAnimation by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val searchDisasterScreenState by viewModel.searchDisasterScreenViewState.collectAsStateWithLifecycle()
    val filterState by viewModel.filterState.collectAsStateWithLifecycle()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        LaunchedEffect(Unit) {
            launch {
                delay(500)
                isTransitionAnimation = false
            }
            searchDisasterScreenState.filterClicked = {
                scope.launch {
                    sheetStateFilter.show()
                }
            }
        }
        Column {
            FilterActiveBar(
                filterState = filterState,
                onRemoveDisasterFilter = { viewModel.onFilterEvent(FilterEvent.OnDisasterChanged(it)) },
                onRemoveProvinceFilter = { viewModel.onFilterEvent(FilterEvent.OnProvinceChanged(it)) },
                onRemoveTimePeriodFilter = {
                    viewModel.onFilterEvent(
                        FilterEvent.OnTimePeriodChanged(null)
                    )
                })
            SearchDisasterListView(
                listSearchResult = searchDisasterScreenState.provinceSearch,
                listRelatedSearch = searchDisasterScreenState.reportsRelated
            )
        }

        ModalBottomSheetLayout(
            sheetState = sheetStateFilter,
            sheetBackgroundColor = MaterialTheme.colorScheme.surface,
            sheetContentColor = MaterialTheme.colorScheme.onSurface,
            sheetContent = {
                FilterSheet(
                    filterState.timePeriodFilter,
                    filterProvince = filterState.provinceFilter,
                    onFilterTimePeriodChanged = {
                        viewModel.onFilterEvent(FilterEvent.OnTimePeriodChanged(it))
                    },
                    onExpandedFilterProvince = {
                        scope.launch {
                            sheetStateLocationPicker.show()
                        }
                    },
                    onItemFilterProvinceClick = {
                        viewModel.onFilterEvent(
                            FilterEvent.OnProvinceChanged(
                                it
                            )
                        )
                    }
                )
            }) {
        }

        SheetLocationPicker(
            sheetState = sheetStateLocationPicker,
            filterState = filterState,
            onItemClick = { viewModel.onFilterEvent(FilterEvent.OnProvinceChanged(it)) })
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun FilterSheet(
    filterTimePeriod: TimePeriod?,
    filterProvince:List<FilterProvinceModel>,
    onFilterTimePeriodChanged: (TimePeriod) -> Unit,
    onExpandedFilterProvince: () -> Unit,
    onItemFilterProvinceClick: (FilterProvinceModel) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        val calendarState = rememberUseCaseState()
        val timeBoundary = LocalDate.now().let { now -> now.minusYears(2)..now }
        val selectedRange = filterTimePeriod?.getRangeTime()

        CalendarDialog(
            state = calendarState,
            selection = CalendarSelection.Period(
                selectedRange = selectedRange
            ) { startDate, endDate ->
                onFilterTimePeriodChanged(TimePeriod(startDate, endDate))
            },
            config = CalendarConfig(
                yearSelection = true,
                monthSelection = true,
                boundary = timeBoundary,
                style = CalendarStyle.MONTH,
            ),
        )

        Column(
            modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp)
            ) {
                Spacer(
                    modifier = Modifier
                        .height(4.dp)
                        .width(24.dp)
                        .clip(ShapeDefaults.ExtraLarge)
                        .background(MaterialTheme.colorScheme.outline)
                        .align(Alignment.TopCenter)

                )
                Text(
                    text = "Filter",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Rentang Waktu",
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .weight(1f)
                            .border(
                                width = 0.5.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable {
                                calendarState.show()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = filterTimePeriod?.startTime?.format(DateTimeFormatter.ISO_DATE)
                                ?: "Start Date"
                        )
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(32.dp)
                            .weight(1f)
                            .border(
                                width = 0.5.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = MaterialTheme.shapes.medium
                            )
                            .clickable {
                                calendarState.show()
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = filterTimePeriod?.endTime?.format(DateTimeFormatter.ISO_DATE)
                                ?: "End Date"
                        )
                    }
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "Lokasi",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text = "See All",
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.clickable {
                            onExpandedFilterProvince()
                        })
                }
                Spacer(modifier = Modifier.height(8.dp))
                FlowRow() {
                    repeat(8) {
                        val item = filterProvince[it]
                        FilterChip(
                            selected = item.isActive,
                            onClick = { onItemFilterProvinceClick(item) },
                            label = {
                                Text(
                                    text = item.name, style = MaterialTheme.typography.labelMedium
                                )
                            })
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }

    }
}

@Composable
fun FilterActiveBar(
    filterState: FilterState,
    onRemoveDisasterFilter: (FilterDisasterModel) -> Unit,
    onRemoveProvinceFilter: (FilterProvinceModel) -> Unit,
    onRemoveTimePeriodFilter: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Filter :",
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        )
        FilterActiveChip(
            filterState = filterState,
            onRemoveDisasterFilter = onRemoveDisasterFilter,
            onRemoveProvinceFilter = onRemoveProvinceFilter,
            onRemoveTimePeriodFilter = onRemoveTimePeriodFilter
        )
    }
}

@Composable
fun FilterActiveChip(
    filterState: FilterState,
    onRemoveDisasterFilter: (FilterDisasterModel) -> Unit,
    onRemoveProvinceFilter: (FilterProvinceModel) -> Unit,
    onRemoveTimePeriodFilter: () -> Unit
) {
    LazyRow(
        userScrollEnabled = true,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        val disasterActive = filterState.disasterFilter.filter { it.selected }
        val provinceActive = filterState.provinceFilter.filter { it.isActive }

        if (disasterActive.isEmpty() && provinceActive.isEmpty() && filterState.timePeriodFilter == null) {
            item {
                Text(
                    text = "No Filter Active",
                    color = MaterialTheme.colorScheme.outline.copy(alpha = 0.7f)
                )
            }
        }

        itemsIndexed(items = disasterActive, key = { _, item -> item.title }) { index, item ->
            AssistChip(
                onClick = { onRemoveDisasterFilter(item) },
                label = { Text(text = item.title) })
        }
        itemsIndexed(items = provinceActive, key = { _, item -> item.id }) { index, item ->
            AssistChip(
                onClick = { onRemoveProvinceFilter(item) },
                label = { Text(text = item.name) })
        }
        if (filterState.timePeriodFilter != null) {
            item {
                AssistChip(
                    onClick = { onRemoveTimePeriodFilter() },
                    label = { Text(text = "Filter By Time") })
            }
        }
    }
}

@Composable
fun SearchDisasterListView(
    listSearchResult: List<FilterProvinceModel>,
    listRelatedSearch: List<ReportModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        itemsIndexed(items = listSearchResult) { index, item ->
            SearchLocationComponent(title = item.name, onClick = {
                //TODO Zoom to point
            })
        }

        item {
            Column(Modifier.padding(vertical = 16.dp)) {
                Text(text = "Related Search", style = MaterialTheme.typography.titleMedium)
            }
        }

        items(items = listRelatedSearch, key = { it.id }) { item ->
            DisasterItem(item = item)
        }
    }
}