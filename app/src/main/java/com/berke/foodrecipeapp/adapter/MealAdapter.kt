package com.berke.foodrecipeapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.berke.foodrecipeapp.R
import com.berke.foodrecipeapp.helper.downloadFromUrl
import com.berke.foodrecipeapp.helper.placHolderProgressBar
import com.berke.foodrecipeapp.model.Meal
import com.berke.foodrecipeapp.view.AllFoodFragmentDirections
import kotlinx.android.synthetic.main.food_recycler_raw.view.*


class MealAdapter (val mealList : ArrayList<Meal>) : RecyclerView.Adapter<MealAdapter.MealViewHolder>() {
    class MealViewHolder (var view: View): RecyclerView.ViewHolder(view) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_recycler_raw,parent,false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {

        holder.view.categoryName.text= mealList[position].strMeal
        holder.view.foodImage.downloadFromUrl(mealList[position].strMealThumb, placHolderProgressBar(holder.view.context))
        holder.view.setOnClickListener{
            val action = AllFoodFragmentDirections.actionAllFoodFragmentToRecipeFragment(idMeal = mealList[position].idMeal.toInt())
            Navigation.findNavController(it).navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    fun updateDataMeal ( newMeal : List<Meal>){
        mealList.clear()
        mealList.addAll(newMeal)
        notifyDataSetChanged()
    }
}