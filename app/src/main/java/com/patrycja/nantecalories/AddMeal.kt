package com.patrycja.nantecalories

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AddMeal : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal)
        val addIngrBtn: Button = findViewById(R.id.addIngrBtn)
        addIngrBtn.setOnClickListener {
            openIngrDialog()
        }
    }
    fun openIngrDialog() {
        var ingrDialog = IngredientDialog()
        ingrDialog.show(supportFragmentManager, "IngredientDialog")
    }
}