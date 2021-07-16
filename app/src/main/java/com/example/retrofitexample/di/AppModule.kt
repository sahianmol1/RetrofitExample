package com.example.retrofitexample.di

import com.example.retrofitexample.api.PlaceHolderAPI
import com.example.retrofitexample.api.PlaceHolderAPI.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun providesPlaceHolderApi(retrofit: Retrofit): PlaceHolderAPI =
        retrofit.create(PlaceHolderAPI::class.java)
}