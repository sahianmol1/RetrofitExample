package com.example.retrofitexample.api

import com.example.retrofitexample.data.Post
import retrofit2.Response
import retrofit2.http.*

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

    @POST("posts")
    suspend fun pushPost(
        @Body post : Post
    ): Response<Post>

    @FormUrlEncoded
    @POST("posts")
    suspend fun pushPost2(
        @Field("userId") userId: Int,
        @Field("id") id: Int,
        @Field("title") title: String,
        @Field("body") body: String,
    ): Response<Post>
}