package com.vzudkin.pizzaapp.presenation.menu.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.vzudkin.pizzaapp.domain.interactor.MainInteractor
import com.vzudkin.pizzaapp.util.Resource
import kotlinx.coroutines.Dispatchers

class MenuViewModel : ViewModel() {
    private val interactor: MainInteractor = MainInteractor()

    fun getDiscounts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = interactor.getDiscounts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, exception.message ?: "Error download data"))
        }
    }

    fun getProducts() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = interactor.getProducts()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, exception.message ?: "Error download data"))
        }
    }

}