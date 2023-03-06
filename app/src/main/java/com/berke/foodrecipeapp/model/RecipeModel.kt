package com.berke.foodrecipeapp.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(
    @SerializedName("meals")
    val meals: List<MealX>
)