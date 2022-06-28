package com.patrycja.nantecalories.fragments.lists

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.patrycja.nantecalories.R
import com.patrycja.nantecalories.fragments.meal.Ingredient

class IngredientListAdapter: RecyclerView.Adapter<IngredientListAdapter.MyViewHolder>() {

    private var ingredientList = emptyList<Ingredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_ingr_row, parent, false))
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ingredientList[position]

        holder.itemName.text = currentItem.name
        holder.itemKcal.text = currentItem.kcal.toString()
    }

    fun setData(ingredient: List<Ingredient>, mealId: Int) {
        Log.d("Listt ingr", ingredient.toString())
        var ingrList = emptyList<Ingredient>()
        for (item in ingredient) {
            if (item.meal == mealId) {
                ingrList += item
            }
        }
        this.ingredientList = ingrList
        notifyDataSetChanged()
    }

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemName: TextView
        var itemKcal: TextView

        init {
            itemName = itemView.findViewById(R.id.ingrRowName)
            itemKcal = itemView.findViewById(R.id.ingrRowKcal)
        }
    }
}