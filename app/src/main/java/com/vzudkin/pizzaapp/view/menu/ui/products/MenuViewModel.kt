package com.vzudkin.pizzaapp.view.menu.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vzudkin.pizzaapp.model.network.ApiHelper
import com.vzudkin.pizzaapp.model.network.NetworkService
import com.vzudkin.pizzaapp.model.network.Repository
import com.vzudkin.pizzaapp.util.Resource
import kotlinx.coroutines.Dispatchers

class MenuViewModel : ViewModel() {
    private val repository: Repository

    init {
        val api = NetworkService.api
        repository = Repository(ApiHelper(api))
    }

    fun getDiscounts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getDiscounts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, exception.message ?: "Error download data"))
        }
    }

    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, exception.message ?: "Error download data"))
        }
    }
}