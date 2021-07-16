package com.example.retrofitexample.singlepost

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.retrofitexample.R
import com.example.retrofitexample.databinding.FragmentSinglePostBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception

@AndroidEntryPoint
class SinglePostFragment : Fragment() {

    private lateinit var binding: FragmentSinglePostBinding
    val viewModel: SinglePostViewModel by viewModels()
    val args: SinglePostFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSinglePostBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            val result = try {
                viewModel.getSinglePost(args.id)
            } catch (e:IOException) {
                Log.e("PostsFragment", "onCreateView: Caught an IO Exception")
                return@launchWhenCreated
            } catch (e: HttpException){
                Log.e("PostsFragment", "onCreateView: Caught Https Exception")
                return@launchWhenCreated
            }

            if (result.isSuccessful && result.body() != null) {
                binding.apply {
                    tvTitle.text = getString(R.string.title_text, result.body()!!.title)
                    tvBody.text = result.body()!!.body
                    tvUserId.text = getString(R.string.user_id_text, result.body()!!.userId)
                }
            } else {
                Log.e("PostsFragment", "Response not successful")
            }
        }
        return binding.root
    }

}