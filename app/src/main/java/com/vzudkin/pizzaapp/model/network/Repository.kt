package com.vzudkin.pizzaapp.model.network

import com.vzudkin.pizzaapp.model.Discount
import com.vzudkin.pizzaapp.model.Product

class Repository(private val apiHelper: ApiHelper) {
    suspend fun getDiscounts(): ArrayList<Discount> = apiHelper.getDiscounts()
    suspend fun getProducts(): ArrayList<Product> = apiHelper.getProducts()
}