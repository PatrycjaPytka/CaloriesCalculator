package com.patrycja.nantecalories.fragments.meal

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.patrycja.nantecalories.data.MealDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MealViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<Meal>>
    private val repository: MealRepository

    init {
        val mealDao = MealDatabase.getDatabase(application).mealDao()
        repository = MealRepository(mealDao)
        readAllData = repository.readAllData
    }

    fun addMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMeal(meal)
        }
    }

    fun deleteMeal(meal: Meal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMeal(meal)
        }
    }
}