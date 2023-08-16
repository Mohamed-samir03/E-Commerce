package com.mosamir.e_commerce.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.mosamir.e_commerce.R
import com.mosamir.e_commerce.databinding.ActivityHomeBinding
import androidx.navigation.ui.NavigationUI.setupWithNavController

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
//    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.home_nav_graph_container) as NavHostFragment
//        navController = navHostFragment.navController
//        setupWithNavController(binding.bottomNavigationView,navController)

        loadFragment(Home())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(Home())
                    true
                }
                R.id.shop -> {
                    loadFragment(Shopping())
                    true
                }
                R.id.favourite -> {
                    loadFragment(Favourite())
                    true
                }
                R.id.profile -> {
                    loadFragment(Profile())
                    true
                }
                else->{true}
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.home_nav_graph_container,fragment)
        transaction.commit()
    }

}