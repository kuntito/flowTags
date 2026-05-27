package com.example.flowtags.ui.components.util

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Stable
class SwipeAndFlingState(
    val offsetX: Animatable<Float, AnimationVector1D>,
    val rotation: Animatable<Float, AnimationVector1D>,
    val dragThreshold: Float,
    val flingTarget: Float,
    val coroutineScope: CoroutineScope,
    val onFlingLeft: () -> Unit,
    val onFlingRight: () -> Unit,
) {
    val snapBack: () -> Unit = {
        coroutineScope.launch {
            launch {
                offsetX.animateTo(
                    0f,
                    animationSpec = spring()
                )
            }
            launch {
                rotation.animateTo(
                    0f,
                    animationSpec = spring()
                )
            }
        }
    }

    val fling: (direction: Float) -> Unit = { direction ->
        coroutineScope.launch {
            launch {
                offsetX.animateTo(
                    flingTarget * direction,
                    animationSpec = tween(300)
                )
            }
            launch {
                rotation.animateTo(
                    30f * direction,
                    animationSpec = tween(300),
                )
            }
        }
         if (direction > 0) onFlingRight() else onFlingLeft()
    }

    val isAtRest: Boolean
        get() = offsetX.value == 0f && rotation.value == 0f
}


@Composable
fun rememberSwipeAndFlingState(
    dragThresholdDp: Int = 50,
    flingTargetDp: Int = 800,
    onFlingLeft: () -> Unit,
    onFlingRight: () -> Unit,
): SwipeAndFlingState {
    val offsetX = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current

    val dragThresholdPx = with(density) {
        dragThresholdDp.dp.toPx()
    }
    val flingTargetPx = with(density) {
        flingTargetDp.dp.toPx()
    }

    return remember(dragThresholdPx, flingTargetPx) {
        SwipeAndFlingState(
            offsetX = offsetX,
            rotation = rotation,
            dragThreshold = dragThresholdPx,
            flingTarget = flingTargetPx,
            coroutineScope = coroutineScope,
            onFlingLeft = onFlingLeft,
            onFlingRight = onFlingRight
        )
    }
}


fun Modifier.swipeAndFling(
    state: SwipeAndFlingState,
) = this
    .graphicsLayer {
        translationX = state.offsetX.value
        rotationZ = state.rotation.value
    }
    .pointerInput(Unit) {
        detectDragGestures(
            onDragEnd = {
                if (state.offsetX.value > state.dragThreshold) {
                    state.fling(1f)
                } else if (state.offsetX.value < -state.dragThreshold) {
                    state.fling(-1f)
                } else {
                    state.snapBack()
                }
            },
            onDrag = { change, dragAmount ->
                change.consume()
                state.coroutineScope.launch {
                    state.offsetX.snapTo(
                        state.offsetX.value + dragAmount.x
                    )
                    state.rotation.snapTo(
                        state.offsetX.value / state.dragThreshold * 15f
                    )
                }
            }
        )
    }