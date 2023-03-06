package com.berke.foodrecipeapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.foodrecipeapp.model.MealX
import com.berke.foodrecipeapp.model.RecipeModel
import com.berke.foodrecipeapp.service.FoodRecipeApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RecipeViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val foodRecipeApiServices = FoodRecipeApiServices()
    val recipeList = MutableLiveData<List<MealX>>()


    fun refreshRecipeData (idMeal: Int){
        getRecipeDataFromAPI(idMeal)
    }


  private fun getRecipeDataFromAPI (idMeal: Int){
        disposable.add(
            foodRecipeApiServices.getRecipeData(idMeal)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RecipeModel>(){

                    override fun onSuccess(t: RecipeModel) {
                        recipeList.value=t.meals
                    }
                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }



                })
        )

    }


}