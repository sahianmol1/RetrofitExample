package com.example.retrofitexample.api

import com.example.retrofitexample.data.Post
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaceHolderAPI {

    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("posts/{id}")
    suspend fun getSinglePost(
        @Path("id") id: Int
    ): Response<Post>
}