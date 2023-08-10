package com.myshopapp.testapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myshopapp.testapp.MyApplication
import com.myshopapp.testapp.databinding.ActivityLoginBinding
import com.myshopapp.testapp.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListener()
    }

    private fun initListener() {
        binding.submit.setOnClickListener {
            if (!binding.email.text.isNullOrEmpty()
                && !binding.password.text.isNullOrEmpty()){
                MyApplication.instance?.userStorage?.setUserSession(binding.email.text.toString())
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }else{
                Toast.makeText(this@LoginActivity, "Please input fields", Toast.LENGTH_SHORT).show()
            }
        }
    }
}