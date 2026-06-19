package com.satvi.dailyinspiration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.repository.QuoteRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val repository: QuoteRepository
) : ViewModel() {
    val favorites: StateFlow<List<Quote>> = repository.observeFavorites().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    fun toggleFavorite(quoteId: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(quoteId)
        }
    }
}
