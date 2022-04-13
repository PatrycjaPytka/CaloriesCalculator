package com.patrycja.nantecalories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
//import java.sql.Connection

class MainActivity : AppCompatActivity() {

//    abstract val connect: Connection
//    val connectionResult: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val caloriesLimitBtn: Button = findViewById(R.id.changeCaloriesLimitBtn)
//        caloriesLimitBtn.setOnClickListener {
//            showCaloriesDialog()
//        }
    }
//    private fun showCaloriesDialog() {
//        val builder = AlertDialog.Builder(this)
//        builder.setTitle(R.string.changeCaloriesPopupTitle)
//    }
}