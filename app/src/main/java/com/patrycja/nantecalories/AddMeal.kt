package com.patrycja.nantecalories

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.patrycja.nantecalories.fragments.meal.Ingredient
import com.patrycja.nantecalories.fragments.meal.Meal
import com.patrycja.nantecalories.fragments.meal.MealViewModel
import java.time.Instant
import java.util.*

class AddMeal : AppCompatActivity()  {

    private lateinit var mMealViewMeal: MealViewModel
     @RequiresApi(Build.VERSION_CODES.O)
     var newMeal = Meal(0, "", (0).toFloat(), Date.from(Instant.now()))

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        mMealViewMeal = ViewModelProvider(this).get(MealViewModel::class.java)

        val saveIngrBtn: Button = findViewById(R.id.saveIngrBtn)
        val saveMealBtn: Button = findViewById(R.id.saveMeal)
        val totalMealKcal: TextView = findViewById(R.id.totalMealKcal)
        val mealName: EditText = findViewById(R.id.addMealName)

        var ingrKcal = (0).toFloat()

        Log.d("mealId", newMeal.mealId.toString())

        saveIngrBtn.setOnClickListener {
            Log.d("adding", "adding ${findViewById<EditText>(R.id.ingrName).text}")
            val newIngrName = findViewById<EditText>(R.id.ingrName).text.toString()
            val newIngrKcal = findViewById<EditText>(R.id.ingrKcal).text.toString().toFloat()
            val newIngr = Ingredient(0, newIngrName, newIngrKcal, newMeal.mealId)

            mMealViewMeal.addIngr(newIngr)
            mMealViewMeal.updateMeal(newMeal.mealId, newMeal.kcal+newIngrKcal)
            ingrKcal += newIngrKcal
            totalMealKcal.text = (ingrKcal).toString()
        }

        saveMealBtn.setOnClickListener {
            mMealViewMeal.addMeal(
                Meal(0,
                    mealName.toString(),
                    ingrKcal.toString().toFloat(),
                    Date.from(Instant.now()),
                )
            )
            Toast.makeText(this, "Posiłek dodany!", Toast.LENGTH_LONG).show()
        }

    }
}