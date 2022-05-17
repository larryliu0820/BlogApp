package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.http.Blog
import com.example.http.BlogHttpClient
import com.example.myapplication.databinding.ActivityBlogDetailsBinding

private const val IMAGE_URL =
    "https://raw.githubusercontent.com/larryliu0820/BlogApp/master/app/src/main/res/drawable/sydney_image.jpeg"
private const val AVATAR_URL =
    "https://raw.githubusercontent.com/larryliu0820/BlogApp/master/app/src/main/res/drawable/avatar_image.jpg"

class BlogDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlogDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBlogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageBack.setOnClickListener {
            finish()
        }

        loadData()
    }

    private fun loadData() {
        BlogHttpClient.loadBlogArticles(onSuccess = { list: List<Blog> ->
            runOnUiThread {
                showData(list[0])
            }
        }, onError = {
//            error: String -> runOnUiThread {
//                showError(error)
//            }
        })
    }

    private fun showData(blog: Blog) {
        binding.textTitle.text = blog.title
        binding.textAuthor.text = blog.author.name
        binding.textDate.text = blog.date
        binding.textRating.text = blog.rating.toString()
        binding.textViews.text = String.format("(%d views)", blog.views)
        binding.textDescription.text = blog.description
        binding.ratingBar.rating = blog.rating

        Glide.with(this).load(blog.image).transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.imageMain)
        Glide.with(this).load(blog.author.avatar).transform(CircleCrop()).transition(
            DrawableTransitionOptions.withCrossFade()
        ).into(binding.imageAvatar)
    }
}