package com.example.retrofitexample.api

import com.example.retrofitexample.api.PlaceHolderAPI.Companion.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private val client = OkHttpClient.Builder().apply {
         addInterceptor(MyInterceptor())
    }.build()

    val api: PlaceHolderAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PlaceHolderAPI::class.java)
    }
}