package com.patrycja.nantecalories.fragments.meal

import androidx.lifecycle.LiveData
import com.patrycja.nantecalories.data.MealDao

class MealRepository(private val mealDao: MealDao) {
    val readAllData: LiveData<List<Meal>> = mealDao.readAllData()

    suspend fun addMeal(meal: Meal) {
        mealDao.addMeal(meal)
    }

    suspend fun deleteMeal(meal: Meal) {
        mealDao.deleteMeal(meal)
    }
}