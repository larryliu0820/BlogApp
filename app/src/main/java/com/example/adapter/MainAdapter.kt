package com.example.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.http.Blog
import com.example.myapplication.databinding.ItemMainBinding

class MainAdapter: ListAdapter<Blog, MainViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }
}

private val DIFF_CALLBACK: DiffUtil.ItemCallback<Blog> = object : DiffUtil.ItemCallback<Blog>() {
    override fun areItemsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Blog, newItem: Blog): Boolean {
        return oldItem == newItem
    }
}

class MainViewHolder(private val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bindTo(blog: Blog) {
        binding.textTitle.text = blog.title
        binding.textDate.text = blog.date

        Glide.with(itemView)
            .load(blog.author.getAvatarUrl()).transform(CircleCrop())
            .transition(DrawableTransitionOptions.withCrossFade()).into(binding.imageAvatar)
    }
}
