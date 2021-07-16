package com.example.retrofitexample.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.example.retrofitexample.R
import com.example.retrofitexample.data.Post


@BindingAdapter("setTitleText")
fun TextView.setTitleText(post: Post) {
    text =context.getString(R.string.title_text, post.title)
}