package com.rperez.flickersearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rperez.flickersearchapp.ui.detail.DetailScreen
import com.rperez.flickersearchapp.ui.main.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "main") {
                composable("main") { MainScreen(navController) }
                composable("detail/{title}/{description}/{author}/{published}/{url}") { backStackEntry ->
                    val title = backStackEntry.arguments?.getString("title") ?: ""
                    val description = backStackEntry.arguments?.getString("description") ?: ""
                    val author = backStackEntry.arguments?.getString("author") ?: ""
                    val published = backStackEntry.arguments?.getString("published") ?: ""
                    val url = backStackEntry.arguments?.getString("url") ?: ""
                    DetailScreen(title, description, author, published, url)
                }
            }
        }
    }
}