package com.kenstarry.snacky.core.domain.use_case

data class CoreUseCases(
    val currentUser: CurrentUser,
    val isLoggedIn: IsLoggedIn,
    val getUserDetails: GetUserDetails
)
