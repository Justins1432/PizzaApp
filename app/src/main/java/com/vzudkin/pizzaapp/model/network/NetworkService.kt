package com.vzudkin.pizzaapp.model.network

import com.vzudkin.pizzaapp.util.URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {
    private val httpLoggingInterceptor = HttpLoggingInterceptor()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    init {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    val api: API = getRetrofit().create(API::class.java)
}