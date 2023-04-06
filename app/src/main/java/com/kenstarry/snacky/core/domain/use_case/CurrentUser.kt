package com.kenstarry.snacky.core.domain.use_case

import com.google.firebase.auth.FirebaseUser
import com.kenstarry.snacky.core.domain.repository.CoreRepository

class CurrentUser(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(): FirebaseUser? = repository.currentUser()
}