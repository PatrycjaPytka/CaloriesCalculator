package com.patrycja.nantecalories.data

import android.content.Context
import androidx.room.*
import com.patrycja.nantecalories.fragments.meal.Ingredient
import com.patrycja.nantecalories.fragments.meal.Meal


@Database(entities = [Meal::class, Ingredient::class], version = 1, exportSchema = false)
@TypeConverters(Coverters::class)
abstract class MealDatabase: RoomDatabase() {
    abstract fun mealDao(): MealDao

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null

        fun getDatabase(context: Context): MealDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "meal_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}