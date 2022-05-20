package com.patrycja.nantecalories

import android.app.Dialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

class IngredientDialog : AppCompatDialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.ingrDialogMess)
                .setPositiveButton(R.string.ingrDialogAdd,
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d("DialogMess", "Add")
                    })
                .setNegativeButton(R.string.ingrDialogCancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d("DialogMess", "Cancel")
                    })
            // Create the AlertDialog object and return it
            builder.setView(R.layout.ingredient_popup)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}