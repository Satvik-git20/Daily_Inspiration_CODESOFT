package com.satvi.dailyinspiration.repository

import com.satvi.dailyinspiration.data.preferences.FontScale
import com.satvi.dailyinspiration.data.preferences.PreferencesManager
import com.satvi.dailyinspiration.data.preferences.UserPreferences
import com.satvi.dailyinspiration.database.QuoteDao
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import kotlin.random.Random

class QuoteRepositoryImpl(
    private val quoteDao: QuoteDao,
    private val preferencesManager: PreferencesManager
) : QuoteRepository {
    override val preferences: Flow<UserPreferences> = preferencesManager.preferencesFlow

    override fun observeFavorites(): Flow<List<Quote>> = quoteDao.observeFavoriteQuotes()

    override fun observeCategories(): Flow<List<String>> = quoteDao.observeCategories()

    override fun observeQuotesByCategory(category: String): Flow<List<Quote>> = quoteDao.observeQuotesByCategory(category)

    override fun searchQuotes(query: String): Flow<List<Quote>> = quoteDao.searchQuotes(query)

    override suspend fun getDailyQuote(forceNew: Boolean): Quote? {
        val allQuotes = quoteDao.getAllQuotes()
        if (allQuotes.isEmpty()) return null

        val userPreferences = preferences.first()
        val today = LocalDate.now().toEpochDay()
        val currentQuoteId = userPreferences.currentQuoteId
        val shouldRefresh = forceNew || userPreferences.lastQuoteEpochDay != today

        val existing = currentQuoteId?.let { quoteDao.getQuoteById(it) }
        if (!shouldRefresh && existing != null) return existing

        // Exclude the current quote to avoid immediate repeats on relaunch or manual refresh.
        val eligibleQuotes = allQuotes.filterNot { it.id == currentQuoteId }
        val nextQuote = (eligibleQuotes.ifEmpty { allQuotes }).random(Random(System.currentTimeMillis()))
        preferencesManager.setCurrentQuoteState(nextQuote.id, today)
        return nextQuote
    }

    override suspend fun toggleFavorite(quoteId: Int) {
        val quote = quoteDao.getQuoteById(quoteId) ?: return
        val updated = quote.copy(
            isFavorite = !quote.isFavorite,
            savedAt = if (quote.isFavorite) null else System.currentTimeMillis()
        )
        quoteDao.updateQuote(updated)
    }

    override suspend fun setTheme(theme: AppTheme) {
        preferencesManager.setTheme(theme)
    }

    override suspend fun setNotifications(enabled: Boolean) {
        preferencesManager.setNotificationsEnabled(enabled)
    }

    override suspend fun setFontScale(value: Int) {
        preferencesManager.setFontScale(FontScale.fromStoredValue(value))
    }
}
