package com.kenstarry.snacky.core.presentation.components

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    context: Context,
    imageUri: Uri?,
    placeholder: Int,
    contentScale: ContentScale = ContentScale.Crop
) {

    imageUri?.let {

        AsyncImage(
            model = ImageRequest.Builder(context)
                .data(it)
                .crossfade(true)
                .placeholder(placeholder)
                .build(),
            contentDescription = "User image",
            contentScale = contentScale,
            modifier = modifier
        )
    }
}