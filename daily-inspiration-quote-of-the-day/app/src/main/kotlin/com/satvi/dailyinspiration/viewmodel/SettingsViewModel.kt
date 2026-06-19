package com.satvi.dailyinspiration.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.satvi.dailyinspiration.data.preferences.UserPreferences
import com.satvi.dailyinspiration.repository.QuoteRepository
import com.satvi.dailyinspiration.ui.theme.AppTheme
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: QuoteRepository
) : ViewModel() {
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

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch { repository.setTheme(theme) }
    }

    fun setNotifications(enabled: Boolean) {
        viewModelScope.launch { repository.setNotifications(enabled) }
    }

    fun setFontScale(value: Int) {
        viewModelScope.launch { repository.setFontScale(value) }
    }
}
