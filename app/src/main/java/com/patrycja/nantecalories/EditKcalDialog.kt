package com.patrycja.nantecalories

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment

class EditKcalDialog : AppCompatDialogFragment() {
//    val dailyKcalLimit: SharedPreferences = MainActivity().getSharedPreferences("dailyKcalLimit",
//        Context.MODE_PRIVATE)
//    val kcalEdit: SharedPreferences.Editor = dailyKcalLimit.edit()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
//            val dailyKcalLimit: SharedPreferences = this.requireContext().getSharedPreferences("dailyKcalLimit", Context.MODE_PRIVATE)
            val builder = AlertDialog.Builder(it)
//            val kcalVal: EditText = EditText(it)
            builder.setMessage(R.string.dailyKcalDialogTitle)
                .setPositiveButton(R.string.dailyKcalDialogSave,
                    DialogInterface.OnClickListener { dialog, id ->
//                        Log.d("KcalDialog Mess", "Saved!"+kcalVal.text.toString().toInt())
//                        val newValue = kcalVal.text.toString().toInt()
//                        kcalEdit.putInt("dailyKcal", newValue)

                    })
                .setNegativeButton(R.string.dailyKcalDialogCancel,
                    DialogInterface.OnClickListener { dialog, id ->
                        Log.d("KcalDialog Mess", "Cancel")
                    })

            builder.setView(R.layout.daily_kcal_popup)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null" )
    }
}