package com.axus.id.model.aggregate

import com.axus.id.model.value.AUID
import com.axus.id.model.value.Permission
import eth.likespro.commons.models.Aggregate
import kotlinx.serialization.Serializable

@Serializable
data class FullPermission(
    val from: AUID?,
    val to: AUID?,
    val context: AUID,
    val permission: Permission
): Aggregate {
    class IsIncorrectException(message: String) : RuntimeException(message)
}