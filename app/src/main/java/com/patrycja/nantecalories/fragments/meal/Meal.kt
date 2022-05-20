package com.patrycja.nantecalories.fragments.meal

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.*


@Entity(tableName="meal_table")
data class Meal(
     @PrimaryKey(autoGenerate=true) val mealId: Int,
     val name: String,
     val kcal: Float,
     val date: Date
)

@Entity(tableName="ingredient_table")
data class Ingredient(
    @PrimaryKey(autoGenerate=true) val id: Int,
    val name: String,
    val mealCreatedId: Int
)

data class MealWithIngredients(
    @Embedded val meal: Meal,
    @Relation(
        parentColumn = "mealId",
        entityColumn = "mealCreatedId"
    )
    val ingredients: List<Ingredient>
)
