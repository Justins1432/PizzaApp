package com.vzudkin.pizzaapp.domain.interactor

import com.vzudkin.pizzaapp.data.model.Discount
import com.vzudkin.pizzaapp.data.model.Product
import com.vzudkin.pizzaapp.data.repository.DataRepositoryImpl
import com.vzudkin.pizzaapp.domain.repository.Repository

class MainInteractor {
    private val repository: Repository = DataRepositoryImpl()

    suspend fun getDiscounts(): ArrayList<Discount> {
        return repository.getDiscounts()
    }

    suspend fun getProducts(): ArrayList<Product> {
        return repository.getProducts()
    }
}