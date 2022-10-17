package com.vzudkin.pizzaapp.model.network

import com.vzudkin.pizzaapp.model.Discount
import com.vzudkin.pizzaapp.model.Product

class ApiHelper(private val api: API) {
    //suspend fun getPizzas(): ArrayList<Pizza> = api.getPizzas()
    suspend fun getDiscounts(): ArrayList<Discount> = api.getDiscounts()
    suspend fun getProducts(): ArrayList<Product> = api.getProducts()
}