package com.patrycja.nantecalories.fragments.meal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.patrycja.nantecalories.data.MealDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {
    val readAllMealData: LiveData<List<Meal>>
    val readAllIngrData: LiveData<List<Ingredient>>

    private val repository: MealRepository

    init {
        val mealDao = MealDatabase.getDatabase(application).mealDao()
        repository = MealRepository(mealDao)
        readAllMealData = repository.readAllMealData
        readAllIngrData = repository.readAllIngrData
    }

    fun addMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMeal(meal)
        }
    }

    fun addIngr(ingredient: Ingredient) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIngr(ingredient)
        }
    }

    fun updateMeal(mealId: Int, newKcal: Float) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMeal(mealId, newKcal)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeal(meal)
        }
    }

    fun deleteIngr(ingredient: Ingredient) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIngr(ingredient)
        }
    }
}