package com.patrycja.nantecalories

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import android.content.Intent



//import java.sql.Connection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val caloriesLimitBtn: Button = findViewById(R.id.changeCaloriesLimitBtn)
        val addMealBtn: Button = findViewById(R.id.addMeal)
        addMealBtn.setOnClickListener {
            var intent = Intent(this, AddMeal::class.java)
            startActivity(intent)
        }
    }
}