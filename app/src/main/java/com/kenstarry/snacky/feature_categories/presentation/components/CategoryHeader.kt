package com.kenstarry.snacky.feature_categories.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.presentation.components.CoilImage
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun CategoryHeader(
    context: Context,
    category: Category?
) {

    category?.let {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                modifier = Modifier
                    .wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                verticalAlignment = Alignment.CenterVertically
            ) {

                //  category Image
                CoilImage(
                    context = context,
                    imageUri = it.categoryImageUrl.toUri(),
                    placeholder = R.drawable.baseline_broken_image_24,
                    modifier = Modifier
                        .size(50.dp)
                )

                //  category title
                Text(
                    text = it.categoryName,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

            }

            //  category items count

        }
    }

}






















