package com.satvi.dailyinspiration.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.satvi.dailyinspiration.model.Quote

@Database(entities = [Quote::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context.applicationContext).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "daily_inspiration.db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}
