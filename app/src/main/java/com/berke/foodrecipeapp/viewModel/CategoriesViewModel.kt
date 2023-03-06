package com.berke.foodrecipeapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.berke.foodrecipeapp.model.CategoriesModel
import com.berke.foodrecipeapp.model.Category
import com.berke.foodrecipeapp.service.FoodRecipeApiServices
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel: ViewModel() {
    private val disposable = CompositeDisposable()
    private val foodRecipeApiServices = FoodRecipeApiServices()
    val  categoryList = MutableLiveData<List<Category>>()



    fun refreshData(){
        getDataFromAPI()
    }


   private fun getDataFromAPI(){


        disposable.add(
            foodRecipeApiServices.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<CategoriesModel>(){

                    override fun onSuccess(t: CategoriesModel) {
                        categoryList.value=t.categories
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                    }

                })
        )
    }

}