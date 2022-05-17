package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences

private const val KEY_LOGIN_STATE = "key_login_state"

class BlogPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("blog_preferences", Context.MODE_PRIVATE)

    var isLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(KEY_LOGIN_STATE, false)
        set(loggedIn: Boolean) = sharedPreferences.edit().putBoolean(KEY_LOGIN_STATE, loggedIn).apply()

}