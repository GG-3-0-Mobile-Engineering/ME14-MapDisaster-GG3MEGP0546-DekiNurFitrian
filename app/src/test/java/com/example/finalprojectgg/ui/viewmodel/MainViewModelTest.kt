package com.example.finalprojectgg.ui.viewmodel

import com.example.finalprojectgg.data.Resource
import com.example.finalprojectgg.data.repository.fake.FakeMapRepository
import com.example.finalprojectgg.domain.usecase.fake.FakeDisasterUseCase
import com.example.finalprojectgg.ui.navigation.Screens
import com.example.finalprojectgg.ui.screens.main.MainScreenEvent
import com.example.finalprojectgg.ui.screens.state.FilterState
import com.example.finalprojectgg.util.MainDispatcherRule
import com.example.finalprojectgg.util.listReportsDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val useCase = FakeDisasterUseCase()
    private val repository = FakeMapRepository()

    private lateinit var viewModel: MainViewModel

    @Before
    fun setup() {
        viewModel = MainViewModel(mapDisasterUseCase = useCase, repo = repository)
    }


    @Test
    fun `main screen state - init show loading`() = runTest {
        assertEquals(
            true,
            viewModel.mapScreenViewState.value.isProgress
        )
    }

    @Test
    fun `main screen state - show list report when there is data`() = runTest {
        val collectJob1 = launch(UnconfinedTestDispatcher()) {
            viewModel.mainScreenViewState.collect()
        }

        useCase.reports.tryEmit(Resource.Success(listReportsDummy))

        assertEquals(
            listReportsDummy,
            viewModel.mapScreenViewState.value.reportModels
        )

        collectJob1.cancel()
    }

    @Test
    fun `filter state - all filter not active when init`() = runTest {
        assertEquals(
            FilterState(),
            viewModel.filterState.value
        )
    }

    @Test
    fun `map screen state - search enabled when screen on map search screen`() = runTest {
        viewModel.onMainScreenEvent(MainScreenEvent.ScreenChanged(Screens.MapDisasterSearch.route))

        assertEquals(
            true,
            viewModel.mainScreenViewState.value.searchEnabled
        )
    }


}