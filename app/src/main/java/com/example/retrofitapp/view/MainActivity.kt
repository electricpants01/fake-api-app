package com.example.retrofitapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.retrofitapp.R
import com.example.retrofitapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navBar = binding.mainNavBar
        val navController = findNavController(R.id.fragment_main_navbar)
        navBar.setupWithNavController(navController)
//        val appBarConf = AppBarConfiguration(setOf(R.id.postVFragment, R.id.postHFragment, R.id.settingsFragment))
//        setupActionBarWithNavController(navController, appBarConf)
    }
}