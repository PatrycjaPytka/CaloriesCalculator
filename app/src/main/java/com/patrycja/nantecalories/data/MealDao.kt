package com.patrycja.nantecalories.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.patrycja.nantecalories.fragments.meal.Meal


@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMeal(meal: Meal)

    @Query("SELECT * FROM meal_table ORDER BY mealId ASC")
    fun readAllData(): LiveData<List<Meal>>

    @Delete
    fun deleteMeal(meal: Meal)
}