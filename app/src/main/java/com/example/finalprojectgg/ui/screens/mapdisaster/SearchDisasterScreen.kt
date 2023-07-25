package com.example.finalprojectgg.ui.screens.mapdisaster

import android.os.Build
import android.util.Range
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.util.toRange
import com.example.finalprojectgg.domain.model.ChipModel
import com.example.finalprojectgg.ui.components.DisasterItem
import com.example.finalprojectgg.ui.components.FilterChipGroup
import com.example.finalprojectgg.ui.components.FlowRow
import com.example.finalprojectgg.ui.viewmodel.MainViewModel
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchDisasterScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel
) {
    Box(modifier = modifier.fillMaxSize()) {
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
            Column {
                FilterSearchDisasterChipActive()
                SearchDisasterListView()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun FilterSheet() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        val calendarState = rememberUseCaseState()
        val timeBoundary = LocalDate.now().let { now -> now.minusYears(2)..now }
        val selectedRange = remember {
            val default =
                LocalDate.now().minusYears(2).let { time -> time.plusDays(5)..time.plusDays(8) }
            mutableStateOf(default.toRange())
        }

        val filterBencana = arrayListOf(
            ChipModel(
                title = "sdfasdf",
                icon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            ),
            ChipModel(
                title = "sdf",
                icon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            ),
            ChipModel(
                title = "fsdf asdf sdfa",
                icon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            ),
            ChipModel(
                title = "assdf",
                icon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            ),
            ChipModel(
                title = "asdfas asdf ",
                icon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            ),
            ChipModel(
                title = "asdf sdfa ",
                icon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            ),
            ChipModel(
                title = "asdfasdf asdf",
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
                title = "asdf sfadf",
                icon = {
                    Icon(imageVector = Icons.Default.Build, contentDescription = null)
                }
            ),
        )
        CalendarDialog(
            state = calendarState,
            selection = CalendarSelection.Period(
                selectedRange = selectedRange.value
            ) { startDate, endDate ->
                selectedRange.value = Range(startDate, endDate)
            },
            config = CalendarConfig(
                yearSelection = true,
                monthSelection = true,
                boundary = timeBoundary,
                style = CalendarStyle.MONTH,
            ),
        )
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
                    Text(text = "Awal Waktu")
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
                    Text(text = "Akhir Waktu")
                }
            }
        }

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Lokasi",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            FlowRow() {
                repeat(filterBencana.size) {
                    val item = filterBencana[it]
                    Chip(modifier = Modifier.padding(end = 8.dp), onClick = { /*TODO*/ }) {
                        Text(text = item.title)
                    }
                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun FilterSearchDisasterChipActive() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp)
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

@Composable
fun SearchDisasterListView() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        items(10) {
            DisasterItem()
        }
    }
}