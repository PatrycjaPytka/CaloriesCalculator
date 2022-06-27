package com.patrycja.nantecalories.fragments.lists

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patrycja.nantecalories.R
import com.patrycja.nantecalories.fragments.meal.MealViewModel

class IngredientListFragment : Fragment() {

    private lateinit var mMealViewModel: MealViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_meal, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.ingrRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = IngredientListAdapter()
        recyclerView.adapter = adapter

        mMealViewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        mMealViewModel.readAllIngrData.observe(viewLifecycleOwner, Observer { ingredient ->
            adapter.setData(ingredient)
        })

        setHasOptionsMenu(true)

        return view
    }
}