package com.vzudkin.pizzaapp.data.repository

import com.vzudkin.pizzaapp.data.model.Discount
import com.vzudkin.pizzaapp.data.model.Product
import com.vzudkin.pizzaapp.data.network.NetworkService
import com.vzudkin.pizzaapp.domain.repository.Repository

class DataRepositoryImpl: Repository {
    private val api = NetworkService.api
    override suspend fun getProducts(): ArrayList<Product> = api.getProducts()
    override suspend fun getDiscounts(): ArrayList<Discount> = api.getDiscounts()
}