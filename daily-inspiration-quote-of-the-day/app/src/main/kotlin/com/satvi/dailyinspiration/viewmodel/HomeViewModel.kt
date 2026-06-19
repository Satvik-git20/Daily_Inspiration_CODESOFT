package com.satvi.dailyinspiration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.repository.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

data class HomeUiState(
    val quote: Quote? = null,
    val isLoading: Boolean = true
)

class HomeViewModel(
    private val repository: QuoteRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        refreshQuote()
    }

    fun refreshQuote(forceNew: Boolean = false) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            _uiState.value = HomeUiState(
                quote = repository.getDailyQuote(forceNew = forceNew),
                isLoading = false
            )
        }
    }

    fun toggleFavorite() {
        val quoteId = _uiState.value.quote?.id ?: return
        viewModelScope.launch {
            repository.toggleFavorite(quoteId)
            refreshQuote(forceNew = false)
        }
    }
}
