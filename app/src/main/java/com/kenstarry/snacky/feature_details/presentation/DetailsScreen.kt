package com.kenstarry.snacky.feature_details.presentation

import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.components.BackPressTopBar
import com.kenstarry.snacky.core.presentation.utils.PaletteGenerator
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_details.domain.model.DetailsEvents
import com.kenstarry.snacky.feature_details.presentation.components.*
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.ui.custom.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    mainNavHostController: NavHostController,
    snackCategory: String,
    snackTitle: String
) {
    val systemController = rememberSystemUiController()

    val direction = Direction(mainNavHostController)
    val coreVM: CoreViewModel = hiltViewModel()
    val detailsVM: DetailsViewModel = hiltViewModel()
    val context = LocalContext.current

    val currentUser = coreVM.getCurrentUser()
    coreVM.onEvent(CoreEvents.GetUserDetails(
        email = currentUser?.email ?: "no email",
        onResponse = {}
    ))

    //  get snack
    detailsVM.onEvent(DetailsEvents.GetSnack(
        categoryTitle = snackCategory,
        snackTitle = snackTitle,
        response = {
            when (it) {
                is Response.Success -> {
                    Toast.makeText(context, "success", Toast.LENGTH_SHORT).show()
                }

                is Response.Failure -> {
                    Toast.makeText(context, "could not retrieve details.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    ))

    coreVM.userDetails.value?.let {
        //  set the favourites list
        if (it.userSnackFavourites.contains(snackTitle)) {
            detailsVM.onEvent(DetailsEvents.ToggleFavoritesButton(true))
        }
    }

    var vibrant by remember { mutableStateOf("#000000") }
    var darkVibrant by remember { mutableStateOf("#000000") }
    var onDarkVibrant by remember { mutableStateOf("#000000") }
    var lightVibrant by remember { mutableStateOf("#000000") }
    var dominantSwatch by remember { mutableStateOf("#000000") }
    var mutedSwatch by remember { mutableStateOf("#000000") }
    var lightMutedSwatch by remember { mutableStateOf("#000000") }
    var darkMutedSwatch by remember { mutableStateOf("#000000") }

    var launchedEffectTriggered by remember {
        mutableStateOf(false)
    }

    detailsVM.snack.value?.let { snack ->

        LaunchedEffect(key1 = launchedEffectTriggered) {

            try {

                val bitmap = PaletteGenerator.convertImageToBitmap(
                    imageUrl = snack.snackImageUrl,
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

        systemController.setStatusBarColor(
            color = Color(parseColor(lightVibrant))
        )

        Scaffold(
            topBar = {
                DetailsTopBar(
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
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .verticalScroll(rememberScrollState())
                        .padding(MaterialTheme.spacing.medium),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                ) {

                    //  header
                    DetailsHeader(
                        snackName = snack.snackName,
                        snackCategory = snackCategory
                    )

                    //  image
                    DetailsImage(
                        context = context,
                        detailsViewModel = detailsVM,
                        snackImage = snack.snackImageUrl,
                        primaryColor = Color(parseColor(lightVibrant)),
                        onFavoriteClicked = {

                            detailsVM.onEvent(
                                DetailsEvents.ToggleFavoritesButton(
                                    !detailsVM.isFavoriteToggled.value
                                )
                            )

                            //  add item to update list
                            currentUser?.email?.let {
                                detailsVM.onEvent(DetailsEvents.UpdateSnackFavorites(
                                    email = it,
                                    snackTitle = snackTitle,
                                    isAdd = detailsVM.isFavoriteToggled.value,
                                    response = { res ->
                                        when (res) {
                                            is Response.Success -> {
                                                if (detailsVM.isFavoriteToggled.value) {
                                                    Toast.makeText(
                                                        context,
                                                        "Snack added to favourites successfully!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                } else {
                                                    Toast.makeText(
                                                        context,
                                                        "Snack removed from favourites successfully!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }
                                            }
                                            is Response.Failure -> {
                                                Toast.makeText(
                                                    context,
                                                    res.error.toString(),
                                                    Toast.LENGTH_SHORT
                                                ).show()
                                            }
                                        }
                                    }
                                ))
                            }
                        }
                    )

                    //  price
                    DetailsPrice(
                        snackPrice = snack.snackPrice,
                        primaryColor = Color(parseColor(lightVibrant))
                    )

                    //  description
                    DetailsDescription(snackDescription = snack.snackDescription)

                }

                //  Add to cart button
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(MaterialTheme.spacing.medium),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    //  add to card button
                    Button(
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(parseColor(lightVibrant)),
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentPadding = PaddingValues(MaterialTheme.spacing.medium)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingCart,
                            contentDescription = "cart icon",
                            tint = Color(parseColor(darkVibrant))
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                        Text(
                            text = "Add To Cart",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

            }

        }
    }

}