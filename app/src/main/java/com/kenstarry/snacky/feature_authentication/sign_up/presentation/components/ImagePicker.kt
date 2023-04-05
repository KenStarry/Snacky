package com.kenstarry.snacky.feature_authentication.sign_up.presentation.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.presentation.components.CoilImage

@Composable
fun ImagePicker(
    modifier: Modifier = Modifier,
    imageUri: Uri?,
    onImageClicked: () -> Unit
) {

    val context = LocalContext.current

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {

        if (imageUri == null) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = "profile image",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .clickable { onImageClicked() },
                contentScale = ContentScale.Crop
            )
        } else {

            //  use coil to display the image from the uri
            CoilImage(
                context = context,
                imageUri = imageUri,
                placeholder = R.drawable.profile,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(100.dp)
                    .clickable { onImageClicked() }
            )
        }

    }
}