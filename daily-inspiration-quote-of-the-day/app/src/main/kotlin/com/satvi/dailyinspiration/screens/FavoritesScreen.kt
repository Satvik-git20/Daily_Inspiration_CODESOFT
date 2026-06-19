package com.satvi.dailyinspiration.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satvi.dailyinspiration.components.EmptyStateView
import com.satvi.dailyinspiration.components.GradientBackground
import com.satvi.dailyinspiration.components.QuoteListItem
import com.satvi.dailyinspiration.components.SectionHeader
import com.satvi.dailyinspiration.viewmodel.FavoritesViewModel

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel) {
    val favorites by viewModel.favorites.collectAsStateWithLifecycle()

    GradientBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(
                title = "Favorites",
                subtitle = "Saved quotes you can revisit any time."
            )
            if (favorites.isEmpty()) {
                Spacer(modifier = Modifier.height(48.dp))
                EmptyStateView(
                    title = "No favorite quotes yet.",
                    subtitle = "Tap the heart on any quote and it will appear here."
                )
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(favorites, key = { it.id }) { quote ->
                        QuoteListItem(
                            quote = quote,
                            showSavedDate = true,
                            onFavoriteClick = { viewModel.toggleFavorite(quote.id) }
                        )
                    }
                }
            }
        }
    }
}
