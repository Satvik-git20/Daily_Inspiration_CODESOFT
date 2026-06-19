package com.satvi.dailyinspiration.navigation

sealed class AppDestination(val route: String) {
    data object Splash : AppDestination("splash")
    data object Home : AppDestination("home")
    data object Favorites : AppDestination("favorites")
    data object Search : AppDestination("search")
    data object Categories : AppDestination("categories")
    data object Settings : AppDestination("settings")
}
