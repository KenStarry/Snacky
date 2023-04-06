package com.kenstarry.snacky.core.domain.model.events

import com.kenstarry.snacky.core.domain.model.Response
import com.kenstarry.snacky.core.domain.model.User

sealed class CoreEvents {

    data class GetUserDetails(
        val email: String,
        val user: (user: User?) -> Unit,
        val onResponse: (response: Response<*>) -> Unit
    ) : CoreEvents()
}
