package com.kenstarry.snacky.core.domain.model

data class Category(
    val categoryName: String,
    val categoryRating: String,
    val categoryImageUrl: String
) {

    constructor() : this("", "", "")
}
