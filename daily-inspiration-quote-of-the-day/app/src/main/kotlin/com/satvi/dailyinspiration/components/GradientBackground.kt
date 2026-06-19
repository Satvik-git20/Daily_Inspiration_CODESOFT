package com.satvi.dailyinspiration.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp

@Composable
fun GradientBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    val bgColor = MaterialTheme.colorScheme.background
    val primaryColor = MaterialTheme.colorScheme.primary
    
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        bgColor,
                        primaryColor.copy(alpha = 0.1f),
                        bgColor
                    )
                )
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        content()
    }
}
