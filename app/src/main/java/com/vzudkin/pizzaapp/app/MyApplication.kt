package com.vzudkin.pizzaapp.app

import android.app.Application
import com.vzudkin.pizzaapp.data.network.API
import com.vzudkin.pizzaapp.data.network.NetworkService

class MyApplication: Application() {
    companion object {
        lateinit var api: API
    }

    override fun onCreate() {
        super.onCreate()
        api = NetworkService.api
    }
}