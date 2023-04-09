package com.kenstarry.snacky.feature_categories.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.kenstarry.snacky.core.domain.model.Snack
import com.kenstarry.snacky.core.domain.model.User
import com.kenstarry.snacky.feature_details.presentation.viewmodel.DetailsViewModel

@Composable
fun CategorySnackImages(
    snackBarHostState: SnackbarHostState,
    userDetails: User,
    detailsVM: DetailsViewModel,
    direction: com.kenstarry.snacky.navigation.Direction,
    snacks: List<Snack>
) {

    val listState = rememberLazyListState()
    val alphabetsList = remember {
        mutableListOf<Char>()
    }

    ('A'..'Z').forEach { alphabet ->
        alphabetsList.add(alphabet)
    }

    //  alphabets lazy column

    LazyColumn(
        content = {
            items(alphabetsList) { alphabet ->

                val snacksInAlphabet =
                    snacks.filter { it.snackName.title.first().uppercase() == alphabet.toString() }

                AnimatedVisibility(visible = snacksInAlphabet.isNotEmpty()) {

                    Log.d(
                        "categoryImages",
                        "Snacks in alphabet : ${snacksInAlphabet}\n"
                    )

                    CategoryAlphabetItem(
                        snackBarHostState = snackBarHostState,
                        userDetails = userDetails,
                        detailsVM = detailsVM,
                        direction = direction,
                        alphabet = alphabet.toString(),
                        snacksInAlphabet = snacksInAlphabet
                    )
                }

            }
        },
        state = listState,
        modifier = Modifier
            .fillMaxSize()
    )

}