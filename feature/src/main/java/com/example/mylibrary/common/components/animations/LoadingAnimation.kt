package com.example.mylibrary.common.components.animations

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import com.example.mylibrary.R
import kotlinx.coroutines.delay

/**
 * Compose Loading animado
 *
 * @param circleColor color del circulo de progreso valor por default 0xFF42478B
 *
 * @param circleSize size del circulo de progreso valor por default 36 dp
 *
 *  @param animationDelay delay de animacion valor por default 400
 *
 *  @param initialAlpha valor inicial del holder por default 0.3f
 *
 * @return Composable Element
 *
 */
@Composable
fun LoadingAnimation3(
    circleColor: Color = Color(0xFF42478B),
    circleSize: Dp = dimensionResource(id = R.dimen.padding_36dp),
    animationDelay: Int = 400,
    initialAlpha: Float = 0.3f,
) {
    // 3 circles
    val circles =
        listOf(
            remember {
                Animatable(initialValue = initialAlpha)
            },
            remember {
                Animatable(initialValue = initialAlpha)
            },
            remember {
                Animatable(initialValue = initialAlpha)
            },
        )

    circles.forEachIndexed { index, animatable ->

        LaunchedEffect(Unit) {
            delay(timeMillis = (animationDelay / circles.size).toLong() * index)

            animatable.animateTo(
                targetValue = 1f,
                animationSpec =
                    infiniteRepeatable(
                        animation =
                            tween(
                                durationMillis = animationDelay,
                            ),
                        repeatMode = RepeatMode.Reverse,
                    ),
            )
        }
    }

    Row(
        modifier = Modifier,
    ) {
        circles.forEachIndexed { index, animatable ->

            if (index != 0) {
                Spacer(modifier = Modifier.width(width = dimensionResource(id = R.dimen.padding_6dp)))
            }

            Box(
                modifier =
                    Modifier
                        .size(size = circleSize)
                        .clip(shape = CircleShape)
                        .background(
                            color =
                                circleColor
                                    .copy(alpha = animatable.value),
                        ),
            ) {
            }
        }
    }
}
