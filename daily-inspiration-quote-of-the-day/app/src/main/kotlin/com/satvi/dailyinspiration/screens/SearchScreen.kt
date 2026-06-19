package com.satvi.dailyinspiration.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satvi.dailyinspiration.components.GradientBackground
import com.satvi.dailyinspiration.components.QuoteListItem
import com.satvi.dailyinspiration.components.SectionHeader
import com.satvi.dailyinspiration.viewmodel.SearchViewModel

@Composable
fun SearchScreen(viewModel: SearchViewModel) {
    val results by viewModel.results.collectAsStateWithLifecycle()
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()

    GradientBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(
                title = "Search",
                subtitle = "Find inspiration by author or keyword."
            )
            OutlinedTextField(
                value = query,
                onValueChange = viewModel::updateQuery,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Search quotes") },
                singleLine = true
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(results, key = { it.id }) { quote ->
                    QuoteListItem(
                        quote = quote,
                        onFavoriteClick = { viewModel.toggleFavorite(quote.id) }
                    )
                }
            }
        }
    }
}
