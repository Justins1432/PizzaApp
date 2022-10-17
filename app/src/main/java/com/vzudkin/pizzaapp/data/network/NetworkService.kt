package com.vzudkin.pizzaapp.data.network

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

    val api: API = getRetrofit().create(API::class.java)

    init {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

}