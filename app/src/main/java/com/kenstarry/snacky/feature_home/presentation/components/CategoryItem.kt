package com.kenstarry.snacky.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.kenstarry.snacky.R
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.core.presentation.components.CoilImage
import com.kenstarry.snacky.ui.custom.spacing

@Composable
fun CategoryItem(
    context: Context,
    category: Category
) {

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
            .wrapContentWidth()
            .height(50.dp)
            .background(MaterialTheme.colorScheme.onSecondary)
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            ),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
        verticalAlignment = Alignment.CenterVertically
    ) {

        //  image
        CoilImage(
            context = context,
            imageUri = category.categoryImageUrl.toUri(),
            placeholder = R.drawable.undraw_ice_cream_s_2_rh,
            modifier = Modifier
                .size(45.dp)
        )

        //  category name
        Text(
            text = category.categoryName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
        )

    }

}








































