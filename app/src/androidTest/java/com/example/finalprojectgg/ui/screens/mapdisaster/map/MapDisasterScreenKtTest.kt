package com.example.finalprojectgg.ui.screens.mapdisaster.map

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberSwipeableState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.unit.dp
import com.example.finalprojectgg.ui.components.States
import com.example.finalprojectgg.ui.screens.mapdisaster.map.state.MapState
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.util.listReportsDummy
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalMaterialApi::class)
class MapDisasterScreenKtTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun mapDisasterScreen_OnLoading() {
        composeTestRule.setContent {
            val swipeableState = rememberSwipeableState(initialValue = States.PEEK)
            val scrollState = rememberLazyListState()
            MapDisasterScreenView(
                mapState = MapState(),
                filterState = FilterState(),
                paddingValues = PaddingValues(top = 80.dp),
                swipeableState = swipeableState,
                scrollState = scrollState,
                onFilterChipClick = {}
            )

        }
        composeTestRule.onAllNodesWithTag("Loading")[0].assertExists()
    }

    @Test
    fun mapDisasterScreen_OnContentShow() {
        composeTestRule.setContent {
            val swipeableState = rememberSwipeableState(initialValue = States.PEEK)
            val scrollState = rememberLazyListState()
            MapDisasterScreenView(
                mapState = MapState(
                    isProgress = false,
                    isContent = true,
                    reportModels = listReportsDummy
                ),
                filterState = FilterState(),
                paddingValues = PaddingValues(top = 80.dp),
                swipeableState = swipeableState,
                scrollState = scrollState,
                onFilterChipClick = {}
            )
        }
        composeTestRule.onAllNodesWithTag("DisasterItem")[0].assertExists()
    }

    @Test
    fun mapDisasterScreen_OnEmptyContent() {
        composeTestRule.setContent {
            val swipeableState = rememberSwipeableState(initialValue = States.PEEK)
            val scrollState = rememberLazyListState()
            MapDisasterScreenView(
                mapState = MapState(isProgress = false, isContent = false, reportModels = listOf()),
                filterState = FilterState(),
                paddingValues = PaddingValues(top = 80.dp),
                swipeableState = swipeableState,
                scrollState = scrollState,
                onFilterChipClick = {}
            )
        }
        composeTestRule.onAllNodesWithTag("Empty")[0].assertExists()
    }

    @Test
    fun mapDisasterScreen_OnErrorOccurred() {
        composeTestRule.setContent {
            val swipeableState = rememberSwipeableState(initialValue = States.PEEK)
            val scrollState = rememberLazyListState()
            MapDisasterScreenView(
                mapState = MapState(
                    isProgress = false,
                    isContent = false,
                    reportModels = listOf(),
                    isError = true,
                    errorMessage = "Test Error Message"
                ),
                filterState = FilterState(),
                paddingValues = PaddingValues(top = 80.dp),
                swipeableState = swipeableState,
                scrollState = scrollState,
                onFilterChipClick = {}
            )
        }
        composeTestRule.onAllNodesWithContentDescription("Error")[0].assertExists()
    }
}