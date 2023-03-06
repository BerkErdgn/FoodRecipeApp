package com.berke.foodrecipeapp.service

import com.berke.foodrecipeapp.model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


//https://www.themealdb.com/api/json/v1/1/    (Base_Url)
//categories.php                              (For Category)


class FoodRecipeApiServices {
    private val BASE_URL ="https://www.themealdb.com/api/json/v1/1/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getData (): Single<CategoriesModel>{
        return api.getCategory()
    }

    fun getMealData (mealName: String): Single<MealsModel>{
        return api.getMeal(mealName)
    }


    fun getRecipeData (id: Int): Single<RecipeModel>{
        return api.getRecipe(id)
    }


}