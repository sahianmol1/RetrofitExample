package com.example.retrofitexample.posts

import com.example.retrofitexample.data.Post

interface PostItemClickListener {

    fun onPostClick(post: Post)
}