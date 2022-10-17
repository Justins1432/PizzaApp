package com.vzudkin.pizzaapp.data.model

import com.google.gson.annotations.SerializedName

data class Product(
    val type: String,
    val image: String,
    val name: String,
    val ingredients: String,
    val price: String
)


