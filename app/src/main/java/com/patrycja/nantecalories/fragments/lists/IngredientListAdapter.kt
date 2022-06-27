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

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_ingr_row, parent, false))
    }

    override fun getItemCount(): Int {
        return ingredientList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = ingredientList[position]

        Log.d("holder ", holder.itemView.toString())
        holder.itemView.findViewById<TextView>(R.id.ingrRowName).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.ingrRowKcal).text = currentItem.kcal.toString()

    }

    fun setData(ingredient: List<Ingredient>) {
        this.ingredientList = ingredient
        notifyDataSetChanged()
    }
}