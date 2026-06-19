package com.satvi.dailyinspiration.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.IosShare
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.satvi.dailyinspiration.ui.theme.Spacing

@Composable
fun QuoteActionRow(
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onRefreshClick: () -> Unit,
    onShareClick: () -> Unit
) {
    val heartScale = animateFloatAsState(
        targetValue = if (isFavorite) 1.2f else 1f,
        animationSpec = spring(dampingRatio = 0.5f),
        label = "favorite-scale"
    )
    val heartColor = animateColorAsState(
        if (isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface,
        label = "favorite-color"
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.Medium)
    ) {
        // Favorite Button
        FilledTonalButton(
            onClick = onFavoriteClick,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Favorite,
                contentDescription = "Favorite",
                modifier = Modifier
                    .size(24.dp)
                    .scale(heartScale.value),
                tint = heartColor.value
            )
        }

        // New Quote Button (Primary Action)
        Button(
            onClick = onRefreshClick,
            modifier = Modifier
                .weight(2f)
                .height(56.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.Refresh,
                contentDescription = "New Quote",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "  New Quote",
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // Share Button
        FilledTonalButton(
            onClick = onShareClick,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.filledTonalButtonColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Icon(
                imageVector = Icons.Rounded.IosShare,
                contentDescription = "Share",
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
