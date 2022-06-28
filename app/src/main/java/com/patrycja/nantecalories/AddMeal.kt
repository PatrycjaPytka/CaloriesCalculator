package com.patrycja.nantecalories

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patrycja.nantecalories.fragments.lists.IngredientListAdapter
import com.patrycja.nantecalories.fragments.meal.Ingredient
import com.patrycja.nantecalories.fragments.meal.Meal
import com.patrycja.nantecalories.fragments.meal.MealViewModel
import java.time.Instant
import java.time.LocalDate
import java.util.*


class AddMeal : AppCompatActivity() {

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<IngredientListAdapter.MyViewHolder>? = null

    private lateinit var mMealViewModel: MealViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)

        var newMeal = Meal(0, "", (0).toFloat(), LocalDate.now().toString())
        val recyclerView = findViewById<RecyclerView>(R.id.ingrRecyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = IngredientListAdapter()
        recyclerView.adapter = adapter

        mMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        mMealViewModel.addMeal(newMeal)

        mMealViewModel.readAllMealData.observe(this, Observer { meal ->
            if (meal.isNotEmpty()) {
                newMeal = meal.first()
            }
        })

        mMealViewModel.readAllIngrData.observe(this, Observer { ingredient ->
            (adapter as IngredientListAdapter).setData(ingredient, newMeal.mealId)
        })

        val displaymetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displaymetrics)
        val a = displaymetrics.heightPixels * 60 / 100
        recyclerView.layoutParams.height = a

        val saveIngrBtn: Button = findViewById(R.id.saveIngrBtn)
        val saveMealBtn: Button = findViewById(R.id.saveMeal)
        val totalMealKcal: TextView = findViewById(R.id.totalMealKcal)
        val mealName: EditText = findViewById(R.id.addMealName)
        val providedIngrName: EditText = findViewById(R.id.ingrName)
        val providedIngrKcal: EditText = findViewById(R.id.ingrKcal)

        var ingrKcal = (0).toFloat()

        Log.d("mealId", newMeal.mealId.toString())

        saveIngrBtn.setOnClickListener {
            val newIngrName = providedIngrName.text.toString()
            val newIngrKcal = providedIngrKcal.text.toString()

            Log.d("values", "$newIngrName $newIngrKcal")
            if (newIngrName.isEmpty() or newIngrKcal.isEmpty()) {
                if (newIngrName.isEmpty()) {
                    Toast.makeText(this, "Nazwa produktu jest wymagana!", Toast.LENGTH_LONG).show()
                }
                if (newIngrKcal.isEmpty()) {
                    Toast.makeText(this, "Kaloryczność produktu jest wymagana!", Toast.LENGTH_LONG)
                        .show()
                }
            } else {

                val newIngr = Ingredient(0, newIngrName, newIngrKcal.toFloat(), newMeal.mealId)

                mMealViewModel.addIngr(newIngr)
                mMealViewModel.updateMeal(newMeal.mealId, newMeal.kcal + newIngrKcal.toFloat())
                ingrKcal += newIngrKcal.toFloat()
                totalMealKcal.text = (ingrKcal).toString()
                providedIngrName.text.clear()
                providedIngrKcal.text.clear()
            }
        }

        saveMealBtn.setOnClickListener {
            if (mealName.text.isNotEmpty()) {
                mMealViewModel.addMeal(
                    Meal(
                        0,
                        mealName.text.toString(),
                        ingrKcal.toString().toFloat(),
                        LocalDate.now().toString(),
                    )
                )
                Toast.makeText(this, "Posiłek dodany!", Toast.LENGTH_LONG).show()
                var intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(
                        this,
                        "Proszę wprowadzić nazwę posiłku!",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }
}
