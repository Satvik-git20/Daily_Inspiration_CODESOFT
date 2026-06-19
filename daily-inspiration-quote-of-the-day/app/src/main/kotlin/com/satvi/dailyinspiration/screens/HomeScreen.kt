package com.satvi.dailyinspiration.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satvi.dailyinspiration.components.GradientBackground
import com.satvi.dailyinspiration.components.QuoteActionRow
import com.satvi.dailyinspiration.components.QuoteCard
import com.satvi.dailyinspiration.ui.theme.Spacing
import com.satvi.dailyinspiration.utils.ShareUtils
import com.satvi.dailyinspiration.viewmodel.HomeViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val today = LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, dd MMMM"))

    GradientBackground {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Spacing.None)
                .padding(bottom = Spacing.Huge),
            verticalArrangement = Arrangement.spacedBy(Spacing.ExtraLarge)
        ) {
            Spacer(modifier = Modifier.height(Spacing.Small))
            
            // 1. Greeting Section
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.Small)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Small)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CalendarMonth,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = today.uppercase(),
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                }
                
                Column {
                    Text(
                        text = "Good Morning 👋",
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Ready to begin a productive day?",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            // 2. Daily Streak Section (Premium Widget)
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
                shape = RoundedCornerShape(Spacing.Medium),
                border = null
            ) {
                Row(
                    modifier = Modifier.padding(Spacing.Medium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(Spacing.Medium)
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(Spacing.Small)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.LocalFireDepartment,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Column {
                        Text(
                            text = "7 Day Streak",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Keep going! You're doing great.",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }

            // 3. Quote of the Day Section
            Column(verticalArrangement = Arrangement.spacedBy(Spacing.Medium)) {
                Text(
                    text = "Quote of the Day",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                QuoteCard(quote = uiState.quote)
            }

            // 4. Action Buttons
            QuoteActionRow(
                isFavorite = uiState.quote?.isFavorite == true,
                onFavoriteClick = viewModel::toggleFavorite,
                onRefreshClick = { viewModel.refreshQuote(forceNew = true) },
                onShareClick = {
                    uiState.quote?.let { ShareUtils.shareQuote(context, it) }
                }
            )
        }
    }
}

@Composable
private fun Box(modifier: Modifier, contentAlignment: Alignment, content: @Composable () -> Unit) {
    androidx.compose.foundation.layout.Box(modifier = modifier, contentAlignment = contentAlignment) {
        content()
    }
}
