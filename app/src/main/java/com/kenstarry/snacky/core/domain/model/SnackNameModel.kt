package com.kenstarry.snacky.core.domain.model

data class SnackNameModel(
    val title: String,
    val subTitle: String
) {
    constructor() : this("", "")
}
