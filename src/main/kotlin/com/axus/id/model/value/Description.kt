package com.axus.id.model.value

import eth.likespro.commons.models.Validatable
import eth.likespro.commons.models.Value
import kotlinx.serialization.Serializable

@Serializable
data class Description(
    val value: String
): Value, Validatable<Description> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class LengthIsInvalidException(message: String) : IsInvalidException(message)

    open class IsIncorrectException(override val message: String) : RuntimeException()
    class LengthIsIncorrectException(message: String) : IsIncorrectException(message)

    init {
        throwIfInvalid()
    }

    override fun throwIfInvalid(): Description {
        if(value.length !in 1..500)
            throw LengthIsInvalidException("Description must be between 1 and 500 characters long")

        return this
    }
}