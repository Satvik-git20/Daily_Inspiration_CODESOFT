package com.satvi.dailyinspiration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.repository.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: QuoteRepository
) : ViewModel() {
    private val query = MutableStateFlow("")
    val searchQuery: StateFlow<String> = query

    val results: StateFlow<List<Quote>> = query
        .debounce(200)
        .flatMapLatest {
            val trimmed = it.trim()
            if (trimmed.isEmpty()) flowOf(emptyList()) else repository.searchQuotes(trimmed)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun updateQuery(newValue: String) {
        query.value = newValue
    }

    fun toggleFavorite(quoteId: Int) {
        viewModelScope.launch { repository.toggleFavorite(quoteId) }
    }
}
