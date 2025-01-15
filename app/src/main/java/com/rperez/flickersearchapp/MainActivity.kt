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

/**
 * The main entry point for the application.
 * This activity is responsible for setting up the navigation graph using Jetpack Compose.
 *
 * @constructor This class is annotated with `@AndroidEntryPoint` to enable Hilt dependency injection.
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Called when the activity is first created. This method sets up the navigation graph
     * and initializes the UI using Jetpack Compose.
     *
     * @param savedInstanceState A `Bundle` containing the saved state of the activity, if available.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content of the activity using Jetpack Compose.
        setContent {
            // Create a NavController to manage navigation between composable screens.
            val navController = rememberNavController()

            /**
             * Define the navigation graph for the app.
             *
             * The navigation graph specifies two destinations:
             * 1. "main": The main screen of the app.
             * 2. "detail": A detail screen that displays additional information based on passed arguments.
             */
            NavHost(
                navController = navController, // Navigation controller to manage destinations
                startDestination = "main" // Initial destination for the navigation graph
            ) {
                /**
                 * Composable destination for the main screen.
                 *
                 * @param navController The navigation controller passed to the `MainScreen`.
                 */
                composable("main") {
                    MainScreen(navController) // Display the main screen
                }

                /**
                 * Composable destination for the detail screen.
                 *
                 * The detail screen accepts the following arguments via the navigation route:
                 * - `title`: The title of the item.
                 * - `description`: A description of the item.
                 * - `author`: The author of the item.
                 * - `published`: The publication date of the item.
                 * - `url`: The URL of the item.
                 */
                composable("detail/{title}/{description}/{author}/{published}/{url}") { backStackEntry ->
                    // Extract arguments from the navigation back stack entry.
                    val title = backStackEntry.arguments?.getString("title") ?: ""
                    val description = backStackEntry.arguments?.getString("description") ?: ""
                    val author = backStackEntry.arguments?.getString("author") ?: ""
                    val published = backStackEntry.arguments?.getString("published") ?: ""
                    val url = backStackEntry.arguments?.getString("url") ?: ""

                    // Display the detail screen with the extracted arguments.
                    DetailScreen(title, description, author, published, url)
                }
            }
        }
    }
}
