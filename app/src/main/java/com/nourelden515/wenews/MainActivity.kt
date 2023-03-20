package com.nourelden515.wenews

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.nourelden515.wenews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setNavigation()

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    private fun setNavigation() {
        setSupportActionBar(binding.toolbar)
        val navView: BottomNavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
        setupAppBar()
        navView.setupWithNavController(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)
        setOnNavigationItemSelectedListener(navView, navController)
        //setupActionBarVisibility(navController)
    }

    private fun setupActionBarVisibility(navController: NavController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    supportActionBar?.hide()
                    binding.toolbar.visibility = View.GONE
                }
                else -> {
                    supportActionBar?.show()
                    binding.toolbar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupAppBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,
                R.id.settingsFragment,
                R.id.exploreFragment
            )
        )
    }

    private fun setOnNavigationItemSelectedListener(
        navView: BottomNavigationView,
        navController: NavController
    ) {
        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    navigateFromHome(navController)
                }
                R.id.settingsFragment -> {
                    navigateFromSettings(navController)
                }
                R.id.exploreFragment -> {
                    navigateFromExplore(navController)
                }
            }
            true
        }
    }

    private fun navigateFromExplore(navController: NavController) {
        when (navController.currentDestination?.id) {
            R.id.homeFragment -> {
                navController.navigate(R.id.action_homeFragment_to_exploreFragment)
            }
            R.id.settingsFragment -> {
                navController.navigate(R.id.action_settingsFragment_to_exploreFragment)
            }
        }
    }

    private fun navigateFromSettings(navController: NavController) {
        when (navController.currentDestination?.id) {
            R.id.homeFragment -> {
                navController.navigate(R.id.action_homeFragment_to_settingsFragment)
            }
            R.id.exploreFragment -> {
                navController.navigate(R.id.action_exploreFragment_to_settingsFragment)
            }
        }
    }

    private fun navigateFromHome(navController: NavController) {
        when (navController.currentDestination?.id) {
            R.id.settingsFragment -> {
                navController.navigate(R.id.action_settingsFragment_to_homeFragment)
            }
            R.id.exploreFragment -> {
                navController.navigate(R.id.action_exploreFragment_to_homeFragment)
            }
        }
    }
}