package com.axus.id.model.value

import eth.likespro.commons.models.Value
import eth.likespro.commons.models.Validatable
import kotlinx.serialization.Serializable

@Serializable
data class Password(
    val value: String
): Value, Validatable<Password> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class LengthIsInvalidException(message: String) : IsInvalidException(message)
    class ContainsSpacesException : IsInvalidException("Password must not contain spaces")

    init {
        throwIfInvalid()
    }

    override fun throwIfInvalid(): Password {
        if(value.length !in 8..128)
            throw LengthIsInvalidException("Password must be between 8 and 128 characters long")
        if(value.contains(" "))
            throw ContainsSpacesException()

        return this
    }
}