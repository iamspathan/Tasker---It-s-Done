package dev.iamspathan.tasker.ui

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.iamspathan.tasker.R
import dev.iamspathan.tasker.R.layout

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        val  navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}

const val ADD_TASK_RESULT_OK = Activity.RESULT_FIRST_USER
const val EDIT_TASK_RESULT_OK = Activity.RESULT_FIRST_USER + 1