package com.axus.id.model.value

import eth.likespro.commons.models.Value
import eth.likespro.commons.models.Validatable
import kotlinx.serialization.Serializable

@Serializable
data class PermissionPattern(
    val value: String
): Value, Validatable<PermissionPattern> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class LengthIsInvalidException(message: String) : IsInvalidException(message)

    init {
        throwIfInvalid()
    }

    companion object {
        val ALL = PermissionPattern(".*")
    }

    override fun throwIfInvalid(): PermissionPattern {
        if (value.length !in 0..135)
            throw LengthIsInvalidException("PermissionPattern must be between 0 and 135 characters long")

        return this
    }
}