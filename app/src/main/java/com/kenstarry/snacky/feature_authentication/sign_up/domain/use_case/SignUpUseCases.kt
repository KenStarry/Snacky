package com.kenstarry.snacky.feature_authentication.sign_up.domain.use_case

data class SignUpUseCases(
    val createAccount: CreateAccount,
    val uploadImageToStorage: UploadImageToStorage
)
