package com.kenstarry.snacky.feature_details.presentation

import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.components.BackPressTopBar
import com.kenstarry.snacky.core.presentation.utils.PaletteGenerator
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    mainNavHostController: NavHostController,
    snackTitle: String
) {

    val direction = Direction(mainNavHostController)
    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current

    //  get snack

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#000000") }
    var lightVibrant by remember { mutableStateOf("#000000") }
    var dominantSwatch by remember { mutableStateOf("#000000") }
    var mutedSwatch by remember { mutableStateOf("#000000") }
    var lightMutedSwatch by remember { mutableStateOf("#000000") }
    var darkMutedSwatch by remember { mutableStateOf("#000000") }

    val imageUrl =
        "https://firebasestorage.googleapis.com/v0/b/snacky-bbcc2.appspot.com/o/snacks%2Fcupcakes%2Fcupcake1.png?alt=media&token=efeabae6-d1b3-4e01-b003-7eb5a9c11d29"

    var launchedEffectTriggered by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = launchedEffectTriggered) {

        try {

            val bitmap = PaletteGenerator.convertImageToBitmap(
                imageUrl = imageUrl,
                context = context
            )

            if (bitmap != null) {
                launchedEffectTriggered = true

                coreVM.onEvent(
                    CoreEvents.SetColorPalette(
                        colors = PaletteGenerator.extractColorsFromBitmap(
                            bitmap
                        )
                    )
                )
            }

        } catch (e: Exception) {
            Toast.makeText(context, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    if (coreVM.colorPalette.value.isNotEmpty() && launchedEffectTriggered) {
        LaunchedEffect(key1 = true) {

            vibrant = coreVM.colorPalette.value["vibrant"] ?: "000000"
            darkVibrant = coreVM.colorPalette.value["darkVibrant"] ?: "000000"
            onDarkVibrant = coreVM.colorPalette.value["onDarkVibrant"] ?: "000000"
            lightVibrant = coreVM.colorPalette.value["lightVibrant"] ?: "000000"
            dominantSwatch = coreVM.colorPalette.value["dominantSwatch"] ?: "000000"
            mutedSwatch = coreVM.colorPalette.value["mutedSwatch"] ?: "000000"
            lightMutedSwatch = coreVM.colorPalette.value["lightMutedSwatch"] ?: "000000"
            darkMutedSwatch = coreVM.colorPalette.value["darkMutedSwatch"] ?: "000000"
        }
    }


    Scaffold(
        topBar = {
            BackPressTopBar(
                title = snackTitle,
                onBackPressed = {
                    direction.navigateUp()
                }
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(parseColor(darkVibrant)))
                    .padding(MaterialTheme.spacing.medium)
            ) {

            }

        }

    }

}