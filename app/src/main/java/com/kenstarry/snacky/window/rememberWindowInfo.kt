package com.kenstarry.snacky.window

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.kenstarry.snacky.window.model.WindowInfo
import com.kenstarry.snacky.window.model.WindowType

@Composable
fun rememberWindowInfo(): WindowInfo {

    val configuration = LocalConfiguration.current

    return WindowInfo(
        screenWidthInfo = when {
            //  phones
            configuration.screenWidthDp < 600 -> WindowType.Compact

            //  small tablets
            configuration.screenWidthDp < 840 -> WindowType.Medium

            //  anything larger than 840dp
            else -> WindowType.Expanded
        },
        screenHeightInfo = when {
            //  phones
            configuration.screenHeightDp < 480 -> WindowType.Compact

            //  small tablets
            configuration.screenHeightDp < 900 -> WindowType.Medium

            //  anything larger than 840dp
            else -> WindowType.Expanded
        },
        screenWidth = configuration.screenWidthDp.dp,
        screenHeight = configuration.screenHeightDp.dp
    )
    
}