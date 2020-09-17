package com.example.tiktrump.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tiktrump.R
import com.example.tiktrump.databinding.ActivityMainBinding
import com.example.tiktrump.ui.fragments.Home
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, Home()).commit()

        binding.changeFragmentButton.setOnClickListener {  }
    }
}