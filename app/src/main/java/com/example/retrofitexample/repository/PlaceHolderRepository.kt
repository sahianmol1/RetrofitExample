package com.example.retrofitexample.repository

import android.content.res.Resources
import android.util.Log
import com.example.retrofitexample.api.PlaceHolderAPI
import com.example.retrofitexample.data.Post
import com.example.retrofitexample.util.Resource
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class PlaceHolderRepository @Inject constructor(val placeHolderAPI: PlaceHolderAPI) {

    suspend fun getPosts(): Response<List<Post>> {
        return placeHolderAPI.getPosts()
    }

    suspend fun getSinglePost(id: Int) = placeHolderAPI.getSinglePost(id)
}