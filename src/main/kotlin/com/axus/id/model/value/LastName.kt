package com.axus.id.model.value

import eth.likespro.commons.models.Validatable
import eth.likespro.commons.models.Value
import kotlinx.serialization.Serializable

@Serializable
data class LastName(
    val value: String
): Value, Validatable<LastName> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class LengthIsInvalidException(message: String) : IsInvalidException(message)

    init {
        throwIfInvalid()
    }

    override fun throwIfInvalid(): LastName {
        if(value.length !in 1..28)
            throw LengthIsInvalidException("LastName must be between 1 and 28 characters long")

        return this
    }
}