package com.ntsolutions.noorportfolio.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

private val RoyalBlueColorScheme = lightColorScheme(
    primary = WhiteText,         // Text on primary backgrounds
    onPrimary = RoyalBlue,       // Buttons & highlights
    secondary = WhiteText,
    onSecondary = RoyalBlue,
    background = RoyalBlue,      // App background
    onBackground = WhiteText,    // Text color
    surface = RoyalBlue,         // Card/Appbar background
    onSurface = WhiteText
)

@Composable
fun NoorPortfolioTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = RoyalBlueColorScheme,
        typography = Typography,
        content = content
    )
}
