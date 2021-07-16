package com.example.retrofitexample.singlepost

import androidx.lifecycle.ViewModel
import com.example.retrofitexample.repository.PlaceHolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SinglePostViewModel @Inject constructor(private val repository: PlaceHolderRepository): ViewModel() {

    suspend fun getSinglePost(id: Int) = repository.getSinglePost(id)
}