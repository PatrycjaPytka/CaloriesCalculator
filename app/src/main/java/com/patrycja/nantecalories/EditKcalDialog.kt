package com.patrycja.nantecalories

import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.lifecycle.ViewModelProvider
import com.patrycja.nantecalories.data.DailyKcalLimitDatabase
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimit
import com.patrycja.nantecalories.fragments.dailyKcalLimit.DailyKcalLimitViewModel
import kotlin.coroutines.suspendCoroutine

class EditKcalDialog : AppCompatDialogFragment() {

    private lateinit var mDailyKcalViewModel: DailyKcalLimitViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {

            mDailyKcalViewModel = ViewModelProvider(this).get(DailyKcalLimitViewModel::class.java)

            val builder = AlertDialog.Builder(it)
            val inflater: LayoutInflater? = activity?.layoutInflater
            val dialogView: View = inflater!!.inflate(R.layout.daily_kcal_popup, null)

            builder.setMessage(R.string.dailyKcalDialogTitle)
                .setPositiveButton(R.string.dailyKcalDialogSave,
                    DialogInterface.OnClickListener { dialog, _ ->
                        val kcalVal = dialogView.findViewById<EditText>(R.id.dailyKcal)
                        val newKcalLimit = DailyKcalLimit(0,kcalVal.text.toString().toFloat())

                        mDailyKcalViewModel.addDailyKcalLimit(newKcalLimit)

                        Toast.makeText(
                            activity?.applicationContext,
                            "Dzienna ilość kalorii została pomyślnie zmieniona",
                            Toast.LENGTH_LONG
                        ).show()
                        dialog.dismiss()
                    })
                .setNegativeButton(R.string.dailyKcalDialogCancel,
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.dismiss()
                    })

            builder.setView(dialogView)
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null" )
    }
}