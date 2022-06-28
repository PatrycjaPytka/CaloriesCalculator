package com.patrycja.nantecalories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.os.Build
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimit
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimitViewModel
import com.patrycja.nantecalories.fragments.lists.MealListAdapter
import com.patrycja.nantecalories.fragments.meal.MealViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainActivity : AppCompatActivity() {

    private lateinit var mDailyKcalViewModel: DailyKcalLimitViewModel
    private lateinit var mMealViewModel: MealViewModel

    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<MealListAdapter.MealViewHolder>? = null

    private var eatenKcal = (0).toFloat()
    private var leftKcal = (0).toFloat()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDailyKcalViewModel = ViewModelProvider(this).get(DailyKcalLimitViewModel::class.java)
        mMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.mealRecyclerView)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        adapter = MealListAdapter()
        recyclerView.adapter = adapter

        val caloriesLimitBtn: Button = findViewById(R.id.changeCaloriesLimitBtn)
        val addMealBtn: Button = findViewById(R.id.addMeal)
        val caloriesLimit: TextView = findViewById(R.id.caloriesLimitTxt)
        val eatenKcalText: TextView = findViewById(R.id.eatenCalAmount)
        val leftKcalText: TextView = findViewById(R.id.caloriesLeftAmount)

        mMealViewModel.readAllMealData.observe(this, Observer { meal ->
            for (item in meal) {
                if (item.name.isNotEmpty()) {
                    val formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                    val formatString = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                    val todayDate = LocalDate.now().format(formatDate)
                    val mealDate = LocalDate.parse(item.date, formatString).format(formatDate)

                    if (todayDate == mealDate) {
                        eatenKcal += item.kcal
                    }
                    eatenKcalText.text = eatenKcal.toString()
                }
            }
            leftKcalText.text =
                (
                    (caloriesLimit.text).toString().toFloat() - (eatenKcalText.text).toString().toFloat()
                ).toString()
        })

        mMealViewModel.readAllMealData.observe(this, Observer { meal ->
            (adapter as MealListAdapter).setData(meal)
        })

        mDailyKcalViewModel.readAllData.observe(this, Observer { dailyKcal ->
            if (dailyKcal.isEmpty()) {
                mDailyKcalViewModel.addDailyKcalLimit(DailyKcalLimit(0, (2000).toFloat()))
                caloriesLimit.text = "2000"
                leftKcal = (2000).toFloat()
            } else {
                caloriesLimit.text = dailyKcal[0].kcal.toString()
                leftKcal = dailyKcal[0].kcal.toString().toFloat()
            }
            var calcKcal = leftKcal.toString().toFloat() - (eatenKcalText.text).toString().toFloat()
            if (calcKcal < (0).toFloat()) {
                leftKcalText.setTextColor(resources.getColor(R.color.red))
            } else if (calcKcal == (0).toFloat()) {
                leftKcalText.setTextColor(resources.getColor(R.color.warning))
            }
            leftKcalText.text = (calcKcal).toString()
        })

        addMealBtn.setOnClickListener {
            var intent = Intent(this, AddMeal::class.java)
            startActivity(intent)
        }
        caloriesLimitBtn.setOnClickListener {
            openEditKcalDialog()
        }
    }

    private fun openEditKcalDialog() {
        var editKcalDialog = EditKcalDialog()
        editKcalDialog.show(supportFragmentManager, "EditKcalDialog")
    }
}