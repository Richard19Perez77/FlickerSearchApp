package com.rperez.flickersearchapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{title}/{description}/{author}/{published}/{url}")
}