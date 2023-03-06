package com.berke.foodrecipeapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.berke.foodrecipeapp.R
import com.berke.foodrecipeapp.adapter.CategoryAdapter
import com.berke.foodrecipeapp.adapter.MealAdapter
import com.berke.foodrecipeapp.viewModel.CategoriesViewModel
import com.berke.foodrecipeapp.viewModel.MealViewModel
import kotlinx.android.synthetic.main.fragment_all_food.*



private lateinit var categoryViewModel : CategoriesViewModel
private  var categoryAdapter= CategoryAdapter(arrayListOf())

private lateinit var mealviewModel: MealViewModel
private var mealAdapter = MealAdapter(arrayListOf())

class AllFoodFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        categoryViewModel = ViewModelProviders.of(this).get(CategoriesViewModel::class.java)
        categoryViewModel.refreshData()

        categoriesRecyclerView.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        categoriesRecyclerView.adapter= categoryAdapter

        observeLiveData()




        mealviewModel = ViewModelProviders.of(this).get(MealViewModel::class.java)
        mealviewModel.refreshMealData("")

        mealRecyclerView.layoutManager = GridLayoutManager(context,2)
        mealRecyclerView.adapter = mealAdapter

        observeLiveDataForMeal()



        categoryAdapter.setClickListener(onClicked)



    }

    private val onClicked = object : CategoryAdapter.OnItemClickListener{
        override fun onClicked(categoriName: String) {
            mealviewModel = ViewModelProviders.of(this@AllFoodFragment).get(MealViewModel::class.java)
            mealviewModel.refreshMealData(categoriName)
            mealNameText.text= "${categoriName} Foods"
        }

    }



    private fun observeLiveData (){
        categoryViewModel.categoryList.observe(viewLifecycleOwner, Observer {
            categoryAdapter.updateDataCategory(it)
        })

    }

    private fun observeLiveDataForMeal(){
        mealviewModel.mealList.observe(viewLifecycleOwner, Observer {
            if (it != null){
                mealRecyclerView.visibility = View.VISIBLE
                mealAdapter.updateDataMeal(it)
            }else{
                println("null")
                mealRecyclerView.visibility = View.GONE
            }

        })
    }



}