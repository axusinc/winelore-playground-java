package com.axus.id.model.value

import eth.likespro.commons.models.Value
import kotlinx.serialization.Serializable

@Serializable
data class Verified(
    val value: Boolean
): Value