package com.example.finalprojectgg.domain.usecase

import com.example.finalprojectgg.data.repository.fake.FakeMapRepository
import com.example.finalprojectgg.domain.model.FilterActive
import com.example.finalprojectgg.ui.screens.state.FilterState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MapDisasterUseCaseImplTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var subjectTest:MapDisasterUseCaseImpl

    private val repo = FakeMapRepository()

    @Before
    fun setup(){
        subjectTest = MapDisasterUseCaseImpl(repo)
    }

    @Test
    fun `filter active - no active filter when initial`() =
        testScope.runTest {
            repo.filterActive.tryEmit(FilterActive())

            assertEquals(
                FilterState(),
                subjectTest.getFilterActive().first()
            )
        }
}