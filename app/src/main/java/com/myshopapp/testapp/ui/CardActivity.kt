package com.myshopapp.testapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.myshopapp.testapp.R
import com.myshopapp.testapp.databinding.ActivityCartBinding
import com.myshopapp.testapp.databinding.ActivityLoginBinding
import com.myshopapp.testapp.databinding.ActivitySplashBinding
import com.myshopapp.testapp.ui.frags.ProductFrag
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardActivity : AppCompatActivity() {
    private val binding by lazy { ActivityCartBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        findViewById<Toolbar>(R.id.id_app_bar).title = "Cart"
        supportFragmentManager.commit {
            replace(R.id.item, ProductFrag.getInstnace(1), "product")
        }
    }
}