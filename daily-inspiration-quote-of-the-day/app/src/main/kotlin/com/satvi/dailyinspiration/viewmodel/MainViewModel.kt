package com.satvi.dailyinspiration.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.satvi.dailyinspiration.AppContainer
import com.satvi.dailyinspiration.data.preferences.UserPreferences
import com.satvi.dailyinspiration.repository.QuoteRepository
import com.satvi.dailyinspiration.ui.theme.AppTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(repository: QuoteRepository) : ViewModel() {
    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    val preferences: StateFlow<UserPreferences> = repository.preferences.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = UserPreferences(
            theme = AppTheme.MINIMALIST_ZEN,
            notificationsEnabled = false,
            fontScale = com.satvi.dailyinspiration.data.preferences.FontScale.MEDIUM,
            currentQuoteId = null,
            lastQuoteEpochDay = null
        )
    )

    init {
        viewModelScope.launch {
            _isLoading.value = false
        }
    }
}

class MainViewModelFactory(
    private val appContainer: AppContainer
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(appContainer.repository) as T
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> FavoritesViewModel(appContainer.repository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(appContainer.repository) as T
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> SettingsViewModel(appContainer.repository) as T
            modelClass.isAssignableFrom(CategoriesViewModel::class.java) -> CategoriesViewModel(appContainer.repository) as T
            modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(appContainer.repository) as T
            else -> error("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}
