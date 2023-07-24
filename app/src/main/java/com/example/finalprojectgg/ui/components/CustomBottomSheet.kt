package com.example.finalprojectgg.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class States {
    EXPANDED,
    COLLAPSED,
    HALF,
    PEEK
}

@ExperimentalMaterialApi
@Composable
fun FullHeightBottomSheet(
    swipeableState: SwipeableState<States>,
    scrollState: LazyListState,
    peekSize: Dp = 60.dp,
    header: @Composable (() -> Unit)? = null,
    body: LazyListScope.() -> Unit
) {
    BoxWithConstraints() {
        val constraintsScope = this
        val peekValue = with(LocalDensity.current) {
            peekSize.toPx()
        }
        val maxHeight = with(LocalDensity.current) {
            constraintsScope.maxHeight.toPx()
        }

        val connection = remember {
            object : NestedScrollConnection {

                override fun onPreScroll(
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return if (delta < 0) {
                        swipeableState.performDrag(delta).toOffset()
                    } else {
                        Offset.Zero
                    }
                }

                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    val delta = available.y
                    return swipeableState.performDrag(delta).toOffset()
                }

                override suspend fun onPreFling(available: Velocity): Velocity {
                    return if (available.y < 0 && (scrollState.firstVisibleItemIndex == 0 && scrollState.firstVisibleItemScrollOffset == 0)) {
                        swipeableState.performFling(available.y)
                        available
                    } else {
                        Velocity.Zero
                    }
                }

                override suspend fun onPostFling(
                    consumed: Velocity,
                    available: Velocity
                ): Velocity {
                    swipeableState.performFling(velocity = available.y)
                    return super.onPostFling(consumed, available)
                }

                private fun Float.toOffset() = Offset(0f, this)
            }
        }

        Box(
            Modifier
                .offset {
                    IntOffset(
                        0,
                        swipeableState.offset.value.roundToInt()
                    )
                }
                .swipeable(
                    state = swipeableState,
                    orientation = Orientation.Vertical,
                    anchors = mapOf(
                        0f to States.EXPANDED,
                        maxHeight to States.COLLAPSED,
                        maxHeight / 1.5f to States.HALF,
                        maxHeight - peekValue to States.PEEK
                    )
                )
                .nestedScroll(connection)

        ) {
            Column(
                Modifier
                    .fillMaxHeight()
                    .background(MaterialTheme.colorScheme.surface),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (header != null) {
                    header()
                }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    state = scrollState,
                ) {
                    body.invoke(this)
                }
            }
        }
    }
}