package com.satvi.dailyinspiration.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

// Spacing System (8dp Grid)
object Spacing {
    val None = 0.dp
    val ExtraSmall = 4.dp
    val Small = 8.dp
    val Medium = 16.dp
    val Large = 24.dp
    val ExtraLarge = 32.dp
    val Huge = 48.dp
}

// Premium Colors (Refined for MD3 Surface Palette)
val PremiumBackground = Color(0xFF0A0F1D) // Deeper Navy
val PremiumSurface = Color(0xFF161D2E)    // Surface L1
val PremiumSurfaceVariant = Color(0xFF1E2638) // Surface L2

val PremiumIndigo = Color(0xFF6366F1)     // Primary
val PremiumEmerald = Color(0xFF10B981)    // Success/Secondary
val PremiumAmber = Color(0xFFF59E0B)      // Accent/Tertiary

val TextPrimary = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF94A3B8)     // Muted Slate

// Minimalist Zen
val ZenBackground = Color(0xFFFDFBF7)
val ZenText = Color(0xFF2C2C2C)
val ZenAccent = Color(0xFF8E8E8E)

// Dark Mode Rest
val RestBackground = Color(0xFF000000)
val RestNeonMint = Color(0xFF98FF98)
val RestTextMuted = Color(0xFFB0B0B0)

// Nature Scenic
val NatureText = Color(0xFFFFFFFF)
val NatureOverlay = Color(0x66000000)

// Bold Editorial
val EditorialTerracotta = Color(0xFFD2691E)
val EditorialOlive = Color(0xFF556B2F)
val EditorialText = Color(0xFFF5F5F5)
val EditorialDark = Color(0xFF1A1A1A)

enum class AppTheme {
    PREMIUM,
    MINIMALIST_ZEN,
    DARK_MODE_REST,
    NATURE_SCENIC,
    BOLD_EDITORIAL
}
