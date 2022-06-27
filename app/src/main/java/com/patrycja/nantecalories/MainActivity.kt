package com.patrycja.nantecalories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.patrycja.nantecalories.data.DailyKcalLimitDatabase
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimit
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimitViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var mDailyKcalViewModel: DailyKcalLimitViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDailyKcalViewModel = ViewModelProvider(this).get(DailyKcalLimitViewModel::class.java)

        val caloriesLimitBtn: Button = findViewById(R.id.changeCaloriesLimitBtn)
        val addMealBtn: Button = findViewById(R.id.addMeal)
        val caloriesLimit: TextView = findViewById(R.id.caloriesLimitTxt)

        mDailyKcalViewModel.readAllData.observe(this, Observer { dailyKcal ->
            caloriesLimit.text = dailyKcal[0].kcal.toString()
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