package com.vzudkin.pizzaapp.model.network

import com.vzudkin.pizzaapp.model.Discount
import com.vzudkin.pizzaapp.model.Product
import retrofit2.http.GET

interface API {
    @GET("/restaurant/getDiscounts.php")
    suspend fun getDiscounts(): ArrayList<Discount>

    @GET("/restaurant/getProducts.php")
    suspend fun getProducts(): ArrayList<Product>
}