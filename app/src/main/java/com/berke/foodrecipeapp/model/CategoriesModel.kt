package com.berke.foodrecipeapp.model

import com.google.gson.annotations.SerializedName

data class CategoriesModel(
    @SerializedName("categories")
    val categories: List<Category>
)