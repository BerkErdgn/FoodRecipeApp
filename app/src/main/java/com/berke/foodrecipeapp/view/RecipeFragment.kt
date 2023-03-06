package com.berke.foodrecipeapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.berke.foodrecipeapp.R
import com.berke.foodrecipeapp.helper.downloadFromUrl
import com.berke.foodrecipeapp.helper.placHolderProgressBar
import com.berke.foodrecipeapp.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_recipe.*


class RecipeFragment : Fragment() {
    private lateinit var recipiViewModel : RecipeViewModel
    private  var idMeal = 52768

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            idMeal = RecipeFragmentArgs.fromBundle(it).idMeal
            println(idMeal)


        }

        recipiViewModel = ViewModelProviders.of(this).get(RecipeViewModel::class.java)
        recipiViewModel.refreshRecipeData(idMeal)

        observeLiveData()


    }
    private fun observeLiveData(){
        recipiViewModel.recipeList.observe(viewLifecycleOwner, Observer {
            println(recipiViewModel.recipeList)
            recipeFoodName.text = it[0].strMeal
            catedoryText.text = it[0].strCategory
            countrtyText.text= it[0].strArea
            val image = it[0].strMealThumb
            context?.let {
                recipeImageView.downloadFromUrl(image, placHolderProgressBar(it))
            }
            instructionsText.text= it[0].strInstructions

            val ingredient ="${it[0].strIngredient1} ${it[0].strMeasure1}\n"+
                            "${it[0].strIngredient2} ${it[0].strMeasure2}\n"+
                            "${it[0].strIngredient3} ${it[0].strMeasure3}\n"+
                            "${it[0].strIngredient4} ${it[0].strMeasure4}\n"+
                            "${it[0].strIngredient5} ${it[0].strMeasure5}\n"+
                            "${it[0].strIngredient6} ${it[0].strMeasure6}\n"+
                            "${it[0].strIngredient7} ${it[0].strMeasure7}\n"+
                            "${it[0].strIngredient8} ${it[0].strMeasure8}\n"+
                            "${it[0].strIngredient9} ${it[0].strMeasure9}\n"+
                            "${it[0].strIngredient10} ${it[0].strMeasure10}\n"+
                            "${it[0].strIngredient11} ${it[0].strMeasure11}\n"+
                            "${it[0].strIngredient12} ${it[0].strMeasure12}\n"+
                            "${it[0].strIngredient13} ${it[0].strMeasure13}\n"+
                            "${it[0].strIngredient14} ${it[0].strMeasure14}\n"+
                            "${it[0].strIngredient15} ${it[0].strMeasure15}\n"+
                            "${it[0].strIngredient16} ${it[0].strMeasure16}\n"+
                            "${it[0].strIngredient17} ${it[0].strMeasure17}\n"+
                            "${it[0].strIngredient18} ${it[0].strMeasure18}\n"+
                            "${it[0].strIngredient19} ${it[0].strMeasure19}\n"+
                            "${it[0].strIngredient20} ${it[0].strMeasure20}\n"
            ingredientsText.text= ingredient



        })

    }


}

