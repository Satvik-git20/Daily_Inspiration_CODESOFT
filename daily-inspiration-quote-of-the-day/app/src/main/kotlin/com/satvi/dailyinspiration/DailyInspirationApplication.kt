package com.satvi.dailyinspiration

import android.app.Application
import com.satvi.dailyinspiration.data.preferences.PreferencesManager
import com.satvi.dailyinspiration.database.AppDatabase
import com.satvi.dailyinspiration.model.Quote
import com.satvi.dailyinspiration.repository.QuoteRepository
import com.satvi.dailyinspiration.repository.QuoteRepositoryImpl
import com.satvi.dailyinspiration.utils.QuoteJsonLoader
import kotlinx.coroutines.runBlocking

class DailyInspirationApplication : Application() {
    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()
        val database = AppDatabase.getInstance(this)
        runBlocking {
            // Seed the local database once so the app stays fully offline afterward.
            if (database.quoteDao().count() == 0) {
                val quotes = QuoteJsonLoader.load(this@DailyInspirationApplication).map {
                    Quote(
                        id = it.id,
                        text = it.text,
                        author = it.author,
                        category = it.category
                    )
                }
                database.quoteDao().insertAll(quotes)
            }
        }
        val preferencesManager = PreferencesManager(this)
        val quoteRepository: QuoteRepository = QuoteRepositoryImpl(
            database.quoteDao(),
            preferencesManager
        )
        appContainer = AppContainer(
            repository = quoteRepository,
            preferencesManager = preferencesManager
        )
    }
}

data class AppContainer(
    val repository: QuoteRepository,
    val preferencesManager: PreferencesManager
)
