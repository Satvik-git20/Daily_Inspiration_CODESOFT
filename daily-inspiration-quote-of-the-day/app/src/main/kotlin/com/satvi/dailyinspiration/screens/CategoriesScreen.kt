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
import com.satvi.dailyinspiration.components.CategoryChipRow
import com.satvi.dailyinspiration.components.GradientBackground
import com.satvi.dailyinspiration.components.QuoteListItem
import com.satvi.dailyinspiration.components.SectionHeader
import com.satvi.dailyinspiration.viewmodel.CategoriesViewModel

@Composable
fun CategoriesScreen(viewModel: CategoriesViewModel) {
    val categories by viewModel.categories.collectAsStateWithLifecycle()
    val quotes by viewModel.quotes.collectAsStateWithLifecycle()
    val selected by viewModel.currentCategory.collectAsStateWithLifecycle()

    GradientBackground {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            SectionHeader(
                title = "Categories",
                subtitle = "Browse quotes by mood, focus, or life theme."
            )
            CategoryChipRow(
                categories = categories,
                selectedCategory = selected,
                onCategorySelected = viewModel::selectCategory
            )
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(quotes, key = { it.id }) { quote ->
                    QuoteListItem(
                        quote = quote,
                        onFavoriteClick = { viewModel.toggleFavorite(quote.id) }
                    )
                }
            }
        }
    }
}
