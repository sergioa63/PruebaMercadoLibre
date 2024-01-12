package com.example.mylibrary.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme =
    darkColorScheme(
        primary = primaryColor,
        secondary = secondaryColor,
        tertiary = blackColor,
        background = writeColor,
        surface = writeColor,
        onPrimary = writeColor,
        onSecondary = writeColor,
        onTertiary = writeColor,
        onBackground = blackColor,
        onSurface = blackColor,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = primaryColor,
        secondary = secondaryColor,
        tertiary = cardColor,
        background = blackColor,
        surface = blackColor,
        onPrimary = writeColor,
        onSecondary = writeColor,
        onTertiary = writeColor,
        onBackground = writeColor,
        onSurface = writeColor,
    )

@Composable
fun MyTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme =
        when {
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
        content = content,
    )
}
