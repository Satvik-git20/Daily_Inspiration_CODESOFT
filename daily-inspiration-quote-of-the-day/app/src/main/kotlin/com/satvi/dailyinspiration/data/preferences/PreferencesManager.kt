package com.satvi.dailyinspiration.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.satvi.dailyinspiration.ui.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "daily_inspiration_prefs")

class PreferencesManager(private val context: Context) {
    private object Keys {
        val theme = stringPreferencesKey("app_theme")
        val notificationsEnabled = booleanPreferencesKey("notifications_enabled")
        val fontScale = intPreferencesKey("font_scale")
        val currentQuoteId = intPreferencesKey("current_quote_id")
        val lastQuoteEpochDay = longPreferencesKey("last_quote_epoch_day")
    }

    val preferencesFlow: Flow<UserPreferences> = context.dataStore.data.map { preferences ->
        UserPreferences(
            theme = preferences[Keys.theme]?.let { enumValueOf<AppTheme>(it) } ?: AppTheme.MINIMALIST_ZEN,
            notificationsEnabled = preferences[Keys.notificationsEnabled] ?: false,
            fontScale = FontScale.fromStoredValue(preferences[Keys.fontScale] ?: FontScale.MEDIUM.value),
            currentQuoteId = preferences[Keys.currentQuoteId],
            lastQuoteEpochDay = preferences[Keys.lastQuoteEpochDay]
        )
    }

    suspend fun setTheme(theme: AppTheme) {
        context.dataStore.edit { it[Keys.theme] = theme.name }
    }

    suspend fun setNotificationsEnabled(enabled: Boolean) {
        context.dataStore.edit { it[Keys.notificationsEnabled] = enabled }
    }

    suspend fun setFontScale(scale: FontScale) {
        context.dataStore.edit { it[Keys.fontScale] = scale.value }
    }

    suspend fun setCurrentQuoteState(id: Int, epochDay: Long) {
        context.dataStore.edit {
            it[Keys.currentQuoteId] = id
            it[Keys.lastQuoteEpochDay] = epochDay
        }
    }
}

data class UserPreferences(
    val theme: AppTheme,
    val notificationsEnabled: Boolean,
    val fontScale: FontScale,
    val currentQuoteId: Int?,
    val lastQuoteEpochDay: Long?
)

enum class FontScale(val value: Int, val scaleFactor: Float) {
    SMALL(0, 0.92f),
    MEDIUM(1, 1.0f),
    LARGE(2, 1.1f);

    companion object {
        fun fromStoredValue(value: Int): FontScale = entries.firstOrNull { it.value == value } ?: MEDIUM
    }
}
