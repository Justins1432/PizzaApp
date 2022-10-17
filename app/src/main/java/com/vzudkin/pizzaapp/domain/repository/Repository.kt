package com.vzudkin.pizzaapp.domain.repository

import com.vzudkin.pizzaapp.data.model.Discount
import com.vzudkin.pizzaapp.data.model.Product

interface Repository {
    suspend fun getProducts(): ArrayList<Product>
    suspend fun getDiscounts(): ArrayList<Discount>
}