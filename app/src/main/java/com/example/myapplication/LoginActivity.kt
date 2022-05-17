package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityLoginBinding
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val preferences: BlogPreferences by lazy {
        BlogPreferences(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (preferences.isLoggedIn) {
            startMainActivity()
            finish()
            return
        }

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener { onLoginClicked() }

        binding.textUsernameLayout.editText?.addTextChangedListener(createTextWatcher(binding.textUsernameLayout))
        binding.textPasswordLayout.editText?.addTextChangedListener(createTextWatcher(binding.textPasswordLayout))
    }

    private fun onLoginClicked() {
        val username = binding.textUsernameLayout.editText?.text.toString()
        val password = binding.textPasswordLayout.editText?.text.toString()
        if (username.isEmpty()) {
            binding.textUsernameLayout.error = "Username is required"
        } else if (password.isEmpty()) {
            binding.textPasswordLayout.error = "Password is required"
        } else if (username != "admin" && password != "admin") {
            showErrorDialog()
        } else {
            performLogin()
        }
    }

    private fun performLogin() {
        preferences.isLoggedIn = true
        binding.textUsernameLayout.isEnabled = false
        binding.textPasswordLayout.isEnabled = false
        binding.loginButton.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                startMainActivity()
                finish()
            }, 2000)
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun showErrorDialog() {
        AlertDialog.Builder(this)
            .setTitle("Login Failed")
            .setMessage("Username or password is incorrect, please try again.")
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun createTextWatcher(textPasswordLayout: TextInputLayout): TextWatcher? {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                textPasswordLayout.error = null
            }

            override fun afterTextChanged(s: Editable) {
            }
        }
    }
}