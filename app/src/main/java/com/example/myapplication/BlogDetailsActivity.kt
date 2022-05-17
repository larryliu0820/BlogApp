package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.myapplication.databinding.ActivityBlogDetailsBinding

private const val IMAGE_URL = "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw/" +
        "3436e16367c8ec2312a0644bebd2694d484eb047/images/sydney_image.jpg"
private const val AVATAR_URL = "https://bitbucket.org/dmytrodanylyk/travel-blog-resources/raw/" +
        "3436e16367c8ec2312a0644bebd2694d484eb047/avatars/avatar1.jpg"

class BlogDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityBlogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(IMAGE_URL).into(binding.imageMain)
        Glide.with(this).load(AVATAR_URL).transform(CircleCrop()).transition(
            DrawableTransitionOptions.withCrossFade()).into(binding.imageAvatar)

        binding.textTitle.text = "G'day from Sydney"
        binding.textAuthor.text = "Grayson Wells"
        binding.textDate.text = "August 2, 2019"
        binding.textRating.text = "4.5"
        binding.textViews.text = "(2687 views)"
        binding.textDescription.text =
            "Australia is one of the most popular travel destinations in the world."
        binding.ratingBar.rating = 4.5f
        binding.imageBack.setOnClickListener {
            finish()
        }
    }
}