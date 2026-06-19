package com.satvi.dailyinspiration.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.satvi.dailyinspiration.model.Quote
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {
    @Query("SELECT COUNT(*) FROM quotes")
    suspend fun count(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<Quote>)

    @Query("SELECT * FROM quotes WHERE id = :id LIMIT 1")
    suspend fun getQuoteById(id: Int): Quote?

    @Query("SELECT * FROM quotes ORDER BY id ASC")
    suspend fun getAllQuotes(): List<Quote>

    @Query("SELECT * FROM quotes WHERE isFavorite = 1 ORDER BY savedAt DESC")
    fun observeFavoriteQuotes(): Flow<List<Quote>>

    @Query(
        """
        SELECT * FROM quotes
        WHERE text LIKE '%' || :query || '%'
        OR author LIKE '%' || :query || '%'
        ORDER BY author ASC
        """
    )
    fun searchQuotes(query: String): Flow<List<Quote>>

    @Query("SELECT DISTINCT category FROM quotes ORDER BY category ASC")
    fun observeCategories(): Flow<List<String>>

    @Query("SELECT * FROM quotes WHERE category = :category ORDER BY author ASC")
    fun observeQuotesByCategory(category: String): Flow<List<Quote>>

    @Update
    suspend fun updateQuote(quote: Quote)
}
