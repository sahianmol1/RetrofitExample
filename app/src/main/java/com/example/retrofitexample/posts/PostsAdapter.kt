package com.example.retrofitexample.posts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitexample.data.Post
import com.example.retrofitexample.databinding.ListItemPostsBinding


class PostsAdapter(val onPostItemClickListener: PostItemClickListener): ListAdapter<Post, PostsAdapter.PostViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ListItemPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }


    inner class PostViewHolder(private val binding: ListItemPostsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post){
            binding.apply {
                post = item
                tvBody.text = item.body
                tvUserId.text = item.userId.toString()

                root.setOnClickListener {
                    onPostItemClickListener.onPostClick(item)
                }
            }
        }
    }

    class DiffCallback: DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }
}