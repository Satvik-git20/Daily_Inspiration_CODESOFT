package com.satvi.dailyinspiration.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class Quote(
    @PrimaryKey val id: Int,
    val text: String,
    val author: String,
    val category: String,
    val isFavorite: Boolean = false,
    val savedAt: Long? = null
)

data class QuoteSeed(
    val id: Int,
    val text: String,
    val author: String,
    val category: String
)
