package com.app.thelocalgym.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF42A5F5),     // Blue 400
    onPrimary = Color(0xFFFFFFFF),    // White

    secondary = Color(0xFF26A69A),    // Teal 400
    onSecondary = Color(0xFFFFFFFF),  // White

    tertiary = Color(0xFFFF7043),     // Orange 400
    onTertiary = Color(0xFFFFFFFF),   // White

    background = Color(0xFF2E2E2E),   // Soft Dark Grey
    onBackground = Color(0xFFE0E0E0), // Light Grey

    surface = Color(0xFF424242),      // Medium Grey
    onSurface = Color(0xFFE0E0E0)     // Light Grey
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1E88E5),     // Blue 600
    onPrimary = Color(0xFFFFFFFF),    // White

    secondary = Color(0xFF26A69A),    // Teal 400
    onSecondary = Color(0xFFFFFFFF),  // White

    tertiary = Color(0xFFFF7043),     // Deep Orange 400
    onTertiary = Color(0xFFFFFFFF),   // White

    background = Color(0xFFF0F0F0),   // Light Grey
    onBackground = Color(0xFF212121), // Dark Grey

    surface = Color(0xFFFFFFFF),      // White
    onSurface = Color(0xFF212121)     // Dark Grey
)

@Composable
fun TheLocalGymTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        } */

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}