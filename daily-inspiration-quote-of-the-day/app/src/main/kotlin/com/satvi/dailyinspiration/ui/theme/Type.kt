package com.satvi.dailyinspiration.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

fun getTypography(theme: AppTheme): Typography {
    return when (theme) {
        AppTheme.PREMIUM -> Typography(
            headlineLarge = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 32.sp,
                lineHeight = 38.sp
            ),
            headlineMedium = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 24.sp,
                lineHeight = 34.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp
            ),
            bodyMedium = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
                fontSize = 16.sp,
                color = TextSecondary
            )
        )
        AppTheme.MINIMALIST_ZEN -> Typography(
            headlineLarge = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Light,
                fontSize = 32.sp,
                letterSpacing = 1.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp
            )
        )
        AppTheme.DARK_MODE_REST -> Typography(
            headlineLarge = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                fontSize = 30.sp,
                color = RestNeonMint
            ),
            bodyLarge = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 17.sp,
                color = RestTextMuted
            )
        )
        AppTheme.NATURE_SCENIC -> Typography(
            headlineLarge = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 34.sp,
                color = NatureText
            ),
            bodyLarge = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                color = NatureText
            )
        )
        AppTheme.BOLD_EDITORIAL -> Typography(
            headlineLarge = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
                lineHeight = 40.sp
            ),
            bodyLarge = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )
    }
}

val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        lineHeight = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 24.sp,
        lineHeight = 30.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
        lineHeight = 26.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 23.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp
    )
)
