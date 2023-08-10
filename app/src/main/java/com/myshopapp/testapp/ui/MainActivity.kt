package com.myshopapp.testapp.ui

import android.os.Bundle
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.myshopapp.testapp.R
import com.myshopapp.testapp.databinding.ActivityLoginBinding
import com.myshopapp.testapp.ui.frags.ProductFrag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        findViewById<Toolbar>(R.id.id_app_bar).title = "Home"
        supportFragmentManager.commit {
            replace(R.id.item, ProductFrag.getInstnace(0), "product")
        }
    }
}