package com.patrycja.nantecalories.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.patrycja.nantecalories.fragments.meal.Ingredient
import com.patrycja.nantecalories.fragments.meal.Meal


@Dao
interface MealDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addMeal(meal: Meal)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addIngr(ingredient: Ingredient)

    @Query("SELECT * FROM meal_table ORDER BY mealId ASC")
    fun readAllMealData(): LiveData<List<Meal>>

    @Query("SELECT * FROM ingredient_table ORDER BY id ASC")
    fun readAllIngrData(): LiveData<List<Ingredient>>

    @Query("UPDATE meal_table SET kcal=:newKcal WHERE mealId=:mealId")
    fun updateMeal(mealId: Int, newKcal: Float)

    @Delete
    fun deleteMeal(meal: Meal)

    @Delete
    fun deleteIngr(ingredient: Ingredient)
}