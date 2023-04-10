package com.kenstarry.snacky

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_settings.presentation.util.SettingsConstants
import com.kenstarry.snacky.feature_settings.presentation.viewmodel.SettingsViewModel
import com.kenstarry.snacky.navigation.graphs.RootNavGraph
import com.kenstarry.snacky.ui.theme.SnackyTheme
import com.kenstarry.snacky.window.rememberWindowInfo
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import java.net.URL

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val settingsViewModel: SettingsViewModel = hiltViewModel()

            SnackyTheme(
                darkTheme = when (settingsViewModel.themeFlow.collectAsState(initial = "").value) {
                    SettingsConstants.themeOptions[0].title -> {
                        //  dark theme enabled
                        true
                    }
                    SettingsConstants.themeOptions[0].title -> {
                        //  dark theme disabled
                        false
                    }
                    SettingsConstants.themeOptions[0].title -> {
                        //  follow system enabled
                        isSystemInDarkTheme()
                    }
                    else -> {
                        isSystemInDarkTheme()
                    }
                }
            ) {

                RootNavGraph(
                    navHostController = rememberNavController()
                )
            }
        }
    }
}
