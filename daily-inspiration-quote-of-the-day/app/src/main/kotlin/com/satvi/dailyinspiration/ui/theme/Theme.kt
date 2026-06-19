package com.satvi.dailyinspiration.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val PremiumColors = darkColorScheme(
    primary = PremiumIndigo,
    secondary = PremiumEmerald,
    tertiary = PremiumAmber,
    background = PremiumBackground,
    surface = PremiumSurface,
    surfaceVariant = PremiumSurfaceVariant,
    onBackground = Color.White,
    onSurface = Color.White,
    onSurfaceVariant = TextSecondary,
    outline = TextSecondary.copy(alpha = 0.5f)
)

private val ZenColors = lightColorScheme(
    primary = ZenAccent,
    background = ZenBackground,
    surface = ZenBackground,
    onBackground = ZenText,
    onSurface = ZenText
)

private val RestColors = darkColorScheme(
    primary = RestNeonMint,
    background = RestBackground,
    surface = Color(0xFF121212),
    onBackground = RestNeonMint,
    onSurface = RestTextMuted
)

private val NatureColors = darkColorScheme(
    primary = NatureText,
    background = Color(0xFF2D3436),
    surface = NatureOverlay,
    onBackground = NatureText,
    onSurface = NatureText
)

private val EditorialColors = lightColorScheme(
    primary = EditorialTerracotta,
    secondary = EditorialOlive,
    background = EditorialText,
    surface = EditorialDark,
    onBackground = EditorialDark,
    onSurface = EditorialText
)

@Composable
fun DailyInspirationTheme(
    theme: AppTheme = AppTheme.PREMIUM,
    fontScale: Float = 1.0f,
    content: @Composable () -> Unit
) {
    val colors = when (theme) {
        AppTheme.PREMIUM -> PremiumColors
        AppTheme.MINIMALIST_ZEN -> ZenColors
        AppTheme.DARK_MODE_REST -> RestColors
        AppTheme.NATURE_SCENIC -> NatureColors
        AppTheme.BOLD_EDITORIAL -> EditorialColors
    }
    
    val view = LocalView.current
    val isDark = theme != AppTheme.MINIMALIST_ZEN && theme != AppTheme.BOLD_EDITORIAL

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colors.background.toArgb()
            window.navigationBarColor = colors.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !isDark
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !isDark
        }
    }

    val baseTypography = getTypography(theme)

    MaterialTheme(
        colorScheme = colors,
        typography = baseTypography.run {
            copy(
                displayLarge = displayLarge.copy(fontSize = displayLarge.fontSize * fontScale),
                headlineLarge = headlineLarge.copy(fontSize = headlineLarge.fontSize * fontScale),
                headlineMedium = headlineMedium.copy(fontSize = headlineMedium.fontSize * fontScale),
                titleLarge = titleLarge.copy(fontSize = titleLarge.fontSize * fontScale),
                bodyLarge = bodyLarge.copy(fontSize = bodyLarge.fontSize * fontScale),
                bodyMedium = bodyMedium.copy(fontSize = bodyMedium.fontSize * fontScale),
                labelLarge = labelLarge.copy(fontSize = labelLarge.fontSize * fontScale)
            )
        },
        content = content
    )
}
