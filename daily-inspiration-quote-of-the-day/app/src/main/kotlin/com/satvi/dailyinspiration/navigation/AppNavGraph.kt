package com.satvi.dailyinspiration.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.Category
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.satvi.dailyinspiration.AppContainer
import com.satvi.dailyinspiration.screens.CategoriesScreen
import com.satvi.dailyinspiration.screens.FavoritesScreen
import com.satvi.dailyinspiration.screens.HomeScreen
import com.satvi.dailyinspiration.screens.SearchScreen
import com.satvi.dailyinspiration.screens.SettingsScreen
import com.satvi.dailyinspiration.screens.SplashScreen
import com.satvi.dailyinspiration.viewmodel.CategoriesViewModel
import com.satvi.dailyinspiration.viewmodel.FavoritesViewModel
import com.satvi.dailyinspiration.viewmodel.HomeViewModel
import com.satvi.dailyinspiration.viewmodel.MainViewModelFactory
import com.satvi.dailyinspiration.viewmodel.SearchViewModel
import com.satvi.dailyinspiration.viewmodel.SettingsViewModel

@Composable
fun AppNavGraph(appContainer: AppContainer) {
    val navController = rememberNavController()
    val factory = MainViewModelFactory(appContainer)
    val bottomDestinations = listOf(
        AppDestination.Home to Pair("Home", Icons.Rounded.AutoAwesome),
        AppDestination.Favorites to Pair("Favorites", Icons.Rounded.Favorite),
        AppDestination.Search to Pair("Search", Icons.Rounded.Search),
        AppDestination.Categories to Pair("Categories", Icons.Rounded.Category),
        AppDestination.Settings to Pair("Settings", Icons.Rounded.Settings)
    )
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = backStackEntry?.destination
    val showBottomBar = currentDestination?.route != AppDestination.Splash.route

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = 0.dp // MD3 standard
                ) {
                    bottomDestinations.forEach { (destination, meta) ->
                        val selected = currentDestination?.hierarchy?.any { it.route == destination.route } == true
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = meta.second,
                                    contentDescription = meta.first,
                                    modifier = Modifier.size(if (selected) 26.dp else 24.dp)
                                )
                            },
                            label = {
                                Text(
                                    text = meta.first,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                    letterSpacing = if (selected) 0.sp else 0.2.sp
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)
                            )
                        )
                    }
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = AppDestination.Splash.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(
                route = AppDestination.Splash.route,
                enterTransition = { fadeIn() },
                exitTransition = { fadeOut() }
            ) {
                SplashScreen {
                    navController.navigate(AppDestination.Home.route) {
                        popUpTo(AppDestination.Splash.route) { inclusive = true }
                    }
                }
            }
            composable(
                route = AppDestination.Home.route,
                enterTransition = {
                    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left) + fadeIn()
                },
                exitTransition = {
                    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left) + fadeOut()
                }
            ) {
                val viewModel: HomeViewModel = viewModel(factory = factory)
                HomeScreen(viewModel = viewModel)
            }
            composable(route = AppDestination.Favorites.route) {
                val viewModel: FavoritesViewModel = viewModel(factory = factory)
                FavoritesScreen(viewModel = viewModel)
            }
            composable(route = AppDestination.Search.route) {
                val viewModel: SearchViewModel = viewModel(factory = factory)
                SearchScreen(viewModel = viewModel)
            }
            composable(route = AppDestination.Categories.route) {
                val viewModel: CategoriesViewModel = viewModel(factory = factory)
                CategoriesScreen(viewModel = viewModel)
            }
            composable(route = AppDestination.Settings.route) {
                val viewModel: SettingsViewModel = viewModel(factory = factory)
                SettingsScreen(viewModel = viewModel)
            }
        }
    }
}
