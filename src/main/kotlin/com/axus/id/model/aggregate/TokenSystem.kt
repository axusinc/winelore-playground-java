package com.axus.id.model.aggregate

import com.axus.id.model.entity.RefreshToken
import com.axus.id.model.entity.Token
import eth.likespro.commons.models.Aggregate
import kotlinx.serialization.Serializable

@Serializable
data class TokenSystem(
    val refreshToken: RefreshToken,
    var token: Token
): Aggregate