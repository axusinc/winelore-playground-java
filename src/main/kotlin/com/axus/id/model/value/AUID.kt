package com.axus.id.model.value

import eth.likespro.commons.models.Value
import eth.likespro.commons.models.Validatable
import kotlinx.serialization.Serializable

@Serializable
data class AUID(
    val value: Long
): Value, Validatable<AUID> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class IsSmallException(message: String) : IsInvalidException(message)

    init {
        throwIfInvalid()
    }

    override fun throwIfInvalid(): AUID {
        if(value < 0)
            throw IsSmallException("AUID must be greater than or equal to 0")

        return this
    }
}