package com.satvi.dailyinspiration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.repository.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val repository: QuoteRepository
) : ViewModel() {
    val categories: StateFlow<List<String>> = repository.observeCategories().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    private val selectedCategory = MutableStateFlow("Motivation")
    val currentCategory: StateFlow<String> = selectedCategory

    val quotes: StateFlow<List<Quote>> = selectedCategory
        .flatMapLatest { repository.observeQuotesByCategory(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    fun selectCategory(category: String) {
        selectedCategory.value = category
    }

    fun toggleFavorite(quoteId: Int) {
        viewModelScope.launch { repository.toggleFavorite(quoteId) }
    }
}
