package com.example.retrofitexample.posts

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitexample.data.Post
import com.example.retrofitexample.repository.PlaceHolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(val repository: PlaceHolderRepository): ViewModel() {

    var responseLiveData = MutableLiveData<Response<List<Post>>>()
    suspend fun getPosts(){
        responseLiveData.value =  repository.getPosts()
    }
}