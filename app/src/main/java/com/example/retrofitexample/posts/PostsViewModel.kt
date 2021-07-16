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
    var pushPostLiveData = MutableLiveData<Response<Post>>()
    var pushPostLiveData2 = MutableLiveData<Response<Post>>()


    suspend fun getPosts(){
        responseLiveData.value =  repository.getPosts()
    }

    fun pushPost(post: Post) = viewModelScope.launch{
        pushPostLiveData.value = repository.pushPost(post)
    }

    fun pushPost2(userId: Int, id: Int, title:String, body: String) = viewModelScope.launch {
        pushPostLiveData2.value = repository.pushPost2(userId, id, title, body)
    }
}