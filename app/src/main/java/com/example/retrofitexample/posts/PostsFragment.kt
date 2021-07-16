package com.example.retrofitexample.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.retrofitexample.data.Post
import com.example.retrofitexample.databinding.FragmentPostsBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

@AndroidEntryPoint
class PostsFragment : Fragment(), PostItemClickListener {
    private lateinit var binding: FragmentPostsBinding
    private val viewModel: PostsViewModel by viewModels()
    private lateinit var adapter: PostsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostsBinding.inflate(layoutInflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        adapter = PostsAdapter(this)
        adapter.setHasStableIds(true)

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel.getPosts()
        }
        viewModel.responseLiveData.observe(viewLifecycleOwner, Observer {

             val response = try{
                it
            } catch (e:IOException) {
                Log.e("PostsFragment", "onCreateView: Caught an IO Exception")
                binding.progressBar.isVisible = false
                return@Observer
            } catch (e:HttpException){
                Log.e("PostsFragment", "onCreateView: Caught Https Exception")
                binding.progressBar.isVisible = false
                return@Observer
            }
            if (response.isSuccessful && response.body()!=null){
                adapter.submitList(response.body())
            } else {
                Log.e("PostsFragment", "Response not successful")
            }
            binding.progressBar.isVisible = false
        })

        binding.recyclerViewPosts.adapter = adapter
        binding.recyclerViewPosts.hasFixedSize()
        return binding.root
    }

    override fun onPostClick(post: Post) {
        val action = PostsFragmentDirections.actionPostsFragmentToSinglePostFragment(post.id)
        findNavController().navigate(action)
    }

}