package com.patrycja.nantecalories.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimit


@Database(entities = [DailyKcalLimit::class], version = 1, exportSchema = false)
abstract class DailyKcalLimitDatabase: RoomDatabase() {
    abstract fun dailyKcalLimitDao(): DailyKcalLimitDao

    companion object {
        @Volatile
        private var INSTANCE: DailyKcalLimitDatabase? = null

        fun getDatabase(context: Context): DailyKcalLimitDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DailyKcalLimitDatabase::class.java,
                    "daily_kcal_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}