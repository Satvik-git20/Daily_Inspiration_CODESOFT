package com.satvi.dailyinspiration.utils

import android.content.Context
import com.satvi.dailyinspiration.model.QuoteSeed
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

object QuoteJsonLoader {
    fun load(context: Context): List<QuoteSeed> {
        val json = context.assets.open("quotes.json").bufferedReader().use { it.readText() }
        val payload = Json { ignoreUnknownKeys = true }.decodeFromString(QuotePayload.serializer(), json)
        return payload.quotes.map { seed ->
            QuoteSeed(
                id = seed.id,
                text = seed.text,
                author = seed.author,
                category = seed.category
            )
        }
    }
}

@Serializable
private data class QuotePayload(
    val quotes: List<QuoteSeedPayload>
)

@Serializable
private data class QuoteSeedPayload(
    val id: Int,
    val text: String,
    val author: String,
    val category: String
)
