package com.axus.id.model.aggregate

import com.axus.id.model.value.PermissionPattern
import com.axus.id.model.value.AUID
import eth.likespro.commons.models.Aggregate
import kotlinx.serialization.Serializable

@Serializable
data class FullPermissionPattern(
    val from: AUID,
    val to: AUID,
    val context: AUID,
    val permissionPattern: PermissionPattern
): Aggregate {
    class IsInvalidException(message: String) : RuntimeException(message)
}