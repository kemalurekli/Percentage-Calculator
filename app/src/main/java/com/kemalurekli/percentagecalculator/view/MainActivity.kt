package com.kemalurekli.percentagecalculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.kemalurekli.percentagecalculator.R
import com.kemalurekli.percentagecalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // ca-app-pub-4590136360115636/9749247028
        // Banner id si

        // Banner TEST ca-app-pub-3940256099942544/6300978111


    }
}