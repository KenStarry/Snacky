package com.kenstarry.snacky.feature_home.presentation.components

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kenstarry.snacky.core.domain.model.Category
import com.kenstarry.snacky.ui.custom.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesSection(
    context: Context,
    categories: List<Category>
) {

    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(1),
        content = {
            items(categories) { category ->
                CategoryItem(
                    context = context,
                    category = category
                )
            }
        },
        state = lazyStaggeredGridState,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        horizontalItemSpacing = MaterialTheme.spacing.medium
    )

}