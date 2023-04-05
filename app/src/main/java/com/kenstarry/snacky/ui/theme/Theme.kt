package com.kenstarry.snacky.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kenstarry.snacky.ui.custom.LocalSpacing
import com.kenstarry.snacky.ui.custom.Spacing

private val DarkColorScheme = darkColorScheme(
    primary = AccentOrange,
    tertiary = AccentOrangeLight,
    onPrimary = DarkBackgroundAlt,
    onSecondary = DarkBackground,
    onSecondaryContainer = TextWhite
)

private val LightColorScheme = lightColorScheme(
    primary = AccentOrange,
    tertiary = AccentOrangeLight,
    onPrimary = LightBackground,
    onSecondary = LightBackgroundAlt,
    onSecondaryContainer = TextBlack
)

@Composable
fun SnackyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val systemUiController = rememberSystemUiController()
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {

            systemUiController.setStatusBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )

            systemUiController.setNavigationBarColor(
                color = colorScheme.onPrimary,
                darkIcons = !darkTheme
            )
        }
    }

    CompositionLocalProvider(
        LocalSpacing provides Spacing()
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}