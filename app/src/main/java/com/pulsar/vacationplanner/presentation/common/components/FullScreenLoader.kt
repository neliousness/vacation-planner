package com.pulsar.vacationplanner.presentation.common.components

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun FullScreenLoader() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.DarkGray.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center // Center the loader
    ) {
        CircularLoader()
    }
}

@Composable
fun CircularLoader() {
    // Define animation transition
    val infiniteTransition = rememberInfiniteTransition(label = "")

    // Animate the rotation angle
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    // Canvas to draw the arc (loader)
    Canvas(
        modifier = Modifier
            .size(100.dp)
            .rotate(rotation) // Apply rotation to the whole canvas
    ) {
        val strokeWidth = 8f
        val arcRadius = size.minDimension / 2 - strokeWidth / 2

        // Draw rotating arc (loader)
        drawArc(
            color = Color.White,
            startAngle = 0f,
            sweepAngle = 270f, // Create an arc of 270 degrees (almost a full circle)
            useCenter = false,
            style = Stroke(width = strokeWidth),
            size = Size(arcRadius * 2, arcRadius * 2),
            topLeft = Offset(
                (size.width - arcRadius * 2) / 2,
                (size.height - arcRadius * 2) / 2
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
fun FullScreenLoaderPreview() {
    FullScreenLoader()
}