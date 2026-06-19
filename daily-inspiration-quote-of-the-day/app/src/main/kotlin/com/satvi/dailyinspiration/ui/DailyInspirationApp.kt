package com.satvi.dailyinspiration.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.satvi.dailyinspiration.AppContainer
import com.satvi.dailyinspiration.navigation.AppNavGraph
import com.satvi.dailyinspiration.ui.theme.DailyInspirationTheme
import com.satvi.dailyinspiration.viewmodel.MainViewModel
import com.satvi.dailyinspiration.viewmodel.MainViewModelFactory

@Composable
fun DailyInspirationApp(appContainer: AppContainer) {
    val mainViewModel: MainViewModel = viewModel(factory = MainViewModelFactory(appContainer))
    val preferences by mainViewModel.preferences.collectAsStateWithLifecycle()

    DailyInspirationTheme(
        theme = preferences.theme,
        fontScale = preferences.fontScale.scaleFactor
    ) {
        AppNavGraph(appContainer = appContainer)
    }
}
