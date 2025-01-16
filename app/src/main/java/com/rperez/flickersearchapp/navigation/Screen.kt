package com.rperez.flickersearchapp.navigation

/**
 * Defines the navigation structure for the app.
 * Each screen in the app is represented as a sealed class.
 *
 * @property route The route string used for navigation.
 */
sealed class Screen(val route: String) {

    /**
     * Represents the home screen of the app.
     *
     * Route: "home"
     */
    object Home : Screen("home")

    /**
     * Represents the detail screen of the app.
     *
     * Route: "detail/{title}/{description}/{author}/{published}/{url}"
     * The route contains placeholders for the following parameters:
     * - **title**: The title of the item to display.
     * - **description**: A description of the item.
     * - **author**: The author of the item.
     * - **published**: The published date of the item.
     * - **url**: The URL of the item's associated content.
     */
    object Detail : Screen("detail/{title}/{description}/{author}/{published}/{url}")
}