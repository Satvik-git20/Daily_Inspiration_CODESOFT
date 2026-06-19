package com.satvi.dailyinspiration.repository

import com.satvi.dailyinspiration.data.preferences.UserPreferences
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
    val preferences: Flow<UserPreferences>
    fun observeFavorites(): Flow<List<Quote>>
    fun observeCategories(): Flow<List<String>>
    fun observeQuotesByCategory(category: String): Flow<List<Quote>>
    fun searchQuotes(query: String): Flow<List<Quote>>
    suspend fun getDailyQuote(forceNew: Boolean = false): Quote?
    suspend fun toggleFavorite(quoteId: Int)
    suspend fun setTheme(theme: AppTheme)
    suspend fun setNotifications(enabled: Boolean)
    suspend fun setFontScale(value: Int)
}
