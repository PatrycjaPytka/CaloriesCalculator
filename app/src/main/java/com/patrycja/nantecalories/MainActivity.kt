package com.patrycja.nantecalories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent
import android.content.SharedPreferences
import android.widget.EditText


class MainActivity : AppCompatActivity() {

//    val dailyKcalLimit: SharedPreferences = getSharedPreferences("dailyKcalLimit", MODE_PRIVATE)
//    val kcalEdit: SharedPreferences.Editor = dailyKcalLimit.edit()
//    val dailyKcalVal = findViewById<EditText>(R.id.dailyKcal).text

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val caloriesLimitBtn: Button = findViewById(R.id.changeCaloriesLimitBtn)
        val addMealBtn: Button = findViewById(R.id.addMeal)

        addMealBtn.setOnClickListener {
            var intent = Intent(this, AddMeal::class.java)
            startActivity(intent)
        }
        caloriesLimitBtn.setOnClickListener {
            openEditKcalDialog()
        }
    }
    fun openEditKcalDialog() {
        var editKcalDialog = EditKcalDialog()
        editKcalDialog.show(supportFragmentManager, "EditKcalDialog")
    }
}