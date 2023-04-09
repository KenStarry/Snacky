package com.kenstarry.snacky.feature_categories.presentation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Fastfood
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.snacky.core.domain.model.events.CoreEvents
import com.kenstarry.snacky.core.presentation.components.CenterBackTopBar
import com.kenstarry.snacky.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.snacky.feature_categories.domain.model.CategoryEvents
import com.kenstarry.snacky.feature_categories.presentation.components.CategoryHeader
import com.kenstarry.snacky.feature_categories.presentation.components.CategorySnackImages
import com.kenstarry.snacky.feature_categories.presentation.viewmodel.CategoryViewModel
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel
import com.kenstarry.snacky.navigation.Direction
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    mainNavHostController: NavHostController,
    category: String
) {

    val categoryVM: CategoryViewModel = hiltViewModel()
    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current
    val detailsVM: DetailsViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)
    val currentUser = coreVM.getCurrentUser()
    val snackBarHostState = remember { SnackbarHostState() }

    //  get current user details
    coreVM.onEvent(
        CoreEvents.GetUserDetails(
            email = currentUser?.email ?: "no email",
            onResponse = {}
        ))


    //  get all category snacks
    categoryVM.onEvent(CategoryEvents.GetSnacksInCategory(
        category = category,
        onResponse = {}
    ))
    //  get category details
    categoryVM.onEvent(CategoryEvents.GetCategoryDetails(
        category = category,
        onResponse = {}
    ))

    Log.d(
        "category", "category: $category" +
                "items count : ${categoryVM.snacksInCategory.value.size}\n"
    )

    Scaffold(
        topBar = {
            CenterBackTopBar(
                title = "Category",
                titleIcon = Icons.Outlined.Fastfood,
                actions = emptyArray(),
                onBackPressed = { /*TODO*/ },
                onActionIconPressed = {}
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState) {

                Snackbar(
                    snackbarData = it,
                    shape = RoundedCornerShape(MaterialTheme.spacing.medium),
                    containerColor = MaterialTheme.colorScheme.onPrimary,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
                    actionColor = MaterialTheme.colorScheme.primary,
                    dismissActionContentColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { contentPadding ->

        if (categoryVM.categoryDetails.value != null &&
            coreVM.userDetails.value != null
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.onPrimary)
                    .padding(contentPadding)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.onPrimary)
                        .padding(MaterialTheme.spacing.medium),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
                ) {

                    //  category header
                    CategoryHeader(
                        context = context,
                        category = categoryVM.categoryDetails.value
                    )

                    //  category images
                    CategorySnackImages(
                        snackBarHostState = snackBarHostState,
                        userDetails = coreVM.userDetails.value!!,
                        detailsVM = detailsVM,
                        direction = direction,
                        snacks = categoryVM.snacksInCategory.value
                    )

                }

            }
        }

    }

}