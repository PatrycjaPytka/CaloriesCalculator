package com.patrycja.nantecalories.fragments.lists

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.patrycja.nantecalories.R
import com.patrycja.nantecalories.fragments.meal.Meal
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MealListAdapter: RecyclerView.Adapter<MealListAdapter.MealViewHolder>() {

    private var mealList = emptyList<Meal>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        return MealViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.custom_meal_row,
                parent,
        false)
            )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MealListAdapter.MealViewHolder, position: Int) {
        val currentItem = mealList[position]

        holder.itemName.text = currentItem.name
        holder.itemKcal.text = currentItem.kcal.toString() + " kcal"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun setData(meal: List<Meal>) {
        var mealLst = emptyList<Meal>()
        for (item in meal) {
            val formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val formatString = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val todayDate = LocalDate.now().format(formatDate)
            val mealDate = LocalDate.parse(item.date, formatString).format(formatDate)

            if (todayDate == mealDate && item.name.isNotEmpty() && (item.kcal.toString().toFloat() > 0F)) {
                mealLst += item
            }
        }
        this.mealList = mealLst
        notifyDataSetChanged()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getHour(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("HH:mm")

//        return LocalDate.parse(date, formatter).format(formatter)
        return date
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    inner class MealViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemName: TextView
        var itemKcal: TextView

        init {
            itemName = itemView.findViewById(R.id.mealRowName)
            itemKcal = itemView.findViewById(R.id.mealRowKcal)
        }
    }
}