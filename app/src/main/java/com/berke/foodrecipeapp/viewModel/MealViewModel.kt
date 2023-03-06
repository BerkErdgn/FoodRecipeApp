package com.berke.foodrecipeapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.foodrecipeapp.model.Meal
import com.berke.foodrecipeapp.model.MealsModel
import com.berke.foodrecipeapp.service.FoodRecipeApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MealViewModel: ViewModel() {

    private val disposable = CompositeDisposable()
    private val foodRecipeApiServices = FoodRecipeApiServices()
    val mealList = MutableLiveData<List<Meal>>()



    fun refreshMealData(mealName: String){
        getMealDataFromAPI(mealName)
    }

    private fun getMealDataFromAPI (mealName: String){

        disposable.add(
            foodRecipeApiServices.getMealData(mealName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<MealsModel>(){
                    override fun onSuccess(t: MealsModel) {
                        mealList.value=t.meals
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}