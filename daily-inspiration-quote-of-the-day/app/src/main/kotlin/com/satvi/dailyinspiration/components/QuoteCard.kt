package com.satvi.dailyinspiration.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.ui.theme.Spacing

@Composable
fun QuoteCard(
    quote: Quote?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // Flat premium look
    ) {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
                .padding(Spacing.ExtraLarge)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .heightIn(min = 320.dp)
                    .fillMaxWidth()
            ) {
                Crossfade(
                    targetState = quote,
                    label = "quote-card-fade",
                    animationSpec = tween(durationMillis = 300)
                ) { currentQuote ->
                    Column(
                        verticalArrangement = Arrangement.spacedBy(Spacing.Large),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = currentQuote?.text?.let { "\"$it\"" } ?: "Loading inspiration...",
                            style = MaterialTheme.typography.headlineMedium.copy(
                                lineHeight = 36.sp,
                                letterSpacing = (-0.5).sp
                            ),
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.onSurface,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                        
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(Spacing.Small)
                        ) {
                            Text(
                                text = currentQuote?.author?.let { "— $it" } ?: "",
                                style = MaterialTheme.typography.bodyLarge,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                fontStyle = FontStyle.Italic
                            )
                            
                            if (currentQuote != null) {
                                Surface(
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.12f),
                                    shape = RoundedCornerShape(Spacing.Small),
                                    modifier = Modifier.padding(top = Spacing.Small)
                                ) {
                                    Text(
                                        text = currentQuote.category.uppercase(),
                                        modifier = Modifier.padding(horizontal = Spacing.Medium, vertical = Spacing.ExtraSmall),
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            letterSpacing = 1.sp
                                        ),
                                        color = MaterialTheme.colorScheme.primary,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
