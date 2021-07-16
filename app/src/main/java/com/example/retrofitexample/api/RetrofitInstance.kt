package com.example.retrofitexample.api

import com.example.retrofitexample.api.PlaceHolderAPI.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val api: PlaceHolderAPI by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(PlaceHolderAPI::class.java)
    }
}