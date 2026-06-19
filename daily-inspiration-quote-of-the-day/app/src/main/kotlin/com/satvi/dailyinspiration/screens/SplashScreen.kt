package com.satvi.dailyinspiration.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.satvi.dailyinspiration.ui.theme.Spacing
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateHome: () -> Unit) {
    val visible = remember { mutableStateOf(false) }
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    val tertiary = MaterialTheme.colorScheme.tertiary

    LaunchedEffect(Unit) {
        visible.value = true
        delay(1800)
        onNavigateHome()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    listOf(
                        primary.copy(alpha = 0.95f),
                        secondary,
                        tertiary.copy(alpha = 0.72f)
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedVisibility(visible = visible.value, enter = fadeIn() + scaleIn()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Spacing.Large)
            ) {
                Icon(
                    imageVector = Icons.Rounded.AutoAwesome,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .size(92.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.14f))
                )
                Text(
                    text = "Daily Inspiration",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = "A fresh moment of clarity every day",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.85f)
                )
            }
        }
    }
}
