package com.berke.foodrecipeapp.service

import com.berke.foodrecipeapp.model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//----For Categories----------------------------------------
//https://www.themealdb.com/api/json/v1/1/    (Base_Url)
//categories.php                              (For Category)
//----For Meals---------------------------------------------
//https://www.themealdb.com/api/json/v1/1/    (Base_Url)
//filter.php?i=Pork                           (For Pork)
//----For Recipe-------------------------------------------
//https://www.themealdb.com/api/json/v1/1/    (Base_Url)
//lookup.php?i=52772                         (For Recipe)

interface Api {
    @GET("categories.php")
    fun getCategory(): Single<CategoriesModel>

    @GET("filter.php")
    fun getMeal(@Query("i") mealName : String ): Single<MealsModel>

    @GET("lookup.php")
    fun getRecipe(@Query("i") id : Int): Single<RecipeModel>

}