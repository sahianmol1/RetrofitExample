package com.example.retrofitexample.repository

import com.example.retrofitexample.api.PlaceHolderAPI
import com.example.retrofitexample.api.RetrofitInstance
import com.example.retrofitexample.data.Post
import retrofit2.Response
import javax.inject.Inject

class PlaceHolderRepository @Inject constructor(val placeHolderAPI: PlaceHolderAPI) {

    suspend fun getPosts(): Response<List<Post>> {
        return placeHolderAPI.getPosts()
    }

    suspend fun getSinglePost(id: Int) = placeHolderAPI.getSinglePost(id)

    suspend fun pushPost(post: Post): Response<Post> {
        return RetrofitInstance.api.pushPost(post)
    }

    suspend fun pushPost2(userId: Int, id: Int, title:String, body: String): Response<Post> = RetrofitInstance.api.pushPost2(userId, id, title, body)
}