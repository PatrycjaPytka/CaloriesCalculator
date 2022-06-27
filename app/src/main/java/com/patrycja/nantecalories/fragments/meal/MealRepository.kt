package com.patrycja.nantecalories.fragments.meal

import androidx.lifecycle.LiveData
import com.patrycja.nantecalories.data.MealDao

class MealRepository(private val mealDao: MealDao) {
    val readAllMealData: LiveData<List<Meal>> = mealDao.readAllMealData()
    val readAllIngrData: LiveData<List<Ingredient>> = mealDao.readAllIngrData()

    suspend fun addMeal(meal: Meal) {
        mealDao.addMeal(meal)
    }

    suspend fun addIngr(ingredient: Ingredient) {
        mealDao.addIngr(ingredient)
    }

    suspend fun updateMeal(mealId: Int, newKcal: Float) {
        mealDao.updateMeal(mealId, newKcal)
    }

    suspend fun deleteMeal(meal: Meal) {
        mealDao.deleteMeal(meal)
    }

    suspend fun deleteIngr(ingredient: Ingredient) {
        mealDao.deleteIngr(ingredient)
    }
}