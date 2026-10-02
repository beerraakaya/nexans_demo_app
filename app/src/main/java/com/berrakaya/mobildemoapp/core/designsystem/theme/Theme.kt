package com.berrakaya.mobildemoapp.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.graphics.Color


private val DarkColorScheme = darkColorScheme(
    primary = NexansRedLight,
    onPrimary = NexansOnRedDark,
    primaryContainer = NexansRedContainerDark,
    onPrimaryContainer = NexansRedContainer,
    secondary = Grey100,
    onSecondary = Grey900,
    background = Dark900,
    onBackground = Grey100,
    surface = Dark800,
    onSurface = Grey100,
    surfaceVariant = Dark600,
    onSurfaceVariant = Grey300,
    surfaceContainerLowest = Dark900,
    surfaceContainerLow = Dark800,
    surfaceContainer = Dark700,
    surfaceContainerHigh = Dark600,
    surfaceContainerHighest = Dark500,
    outline = Dark500,
    outlineVariant = Dark600,
    error = ErrorDark,
    onError = NexansOnRedDark,
)

private val LightColorScheme = lightColorScheme(
    primary = NexansRed,
    onPrimary = Color.White,
    primaryContainer = NexansRedContainer,
    onPrimaryContainer = NexansOnRedContainer,
    secondary = Grey900,
    onSecondary = Color.White,
    background = Grey50,
    onBackground = Grey900,
    surface = Color.White,
    onSurface = Grey900,
    surfaceVariant = Grey100,
    onSurfaceVariant = Grey600,
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Grey50,
    surfaceContainer = Color.White,
    surfaceContainerHigh = Grey100,
    surfaceContainerHighest = Grey200,
    outline = Grey300,
    outlineVariant = Grey200,
    error = ErrorLight,
    onError = Color.White,
)

@Composable
fun NexansTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = NexansTypography,
        shapes = NexansShapes,
        content = content,
    )
}