package com.patrycja.nantecalories.fragments.meal
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName="meal_table")
data class Meal(
    @PrimaryKey(autoGenerate = true) val mealId: Int,
    val name: String,
    val kcal: Float,
    val date: Date
)

@Entity(tableName="ingredient_table")
data class Ingredient(
    @PrimaryKey(autoGenerate=true) val id: Int,
    val name: String,
    val kcal: Float,
    val meal: Int,
)

