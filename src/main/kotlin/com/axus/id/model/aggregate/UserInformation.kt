package com.axus.id.model.aggregate

import com.axus.id.model.entity.Credentials
import com.axus.id.model.entity.Profile
import com.axus.id.model.entity.Variation
import kotlinx.serialization.Serializable

@Serializable
data class UserInformation(
    val credentials: Credentials,
    val profile: Profile,
    val variation: Variation
)