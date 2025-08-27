package com.axus.id.model.value

import eth.likespro.commons.models.Value
import eth.likespro.commons.models.Validatable
import kotlinx.serialization.Serializable

@Serializable
data class Username(
    val value: String
): Value, Validatable<Username> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class LengthIsInvalidException(message: String) : IsInvalidException(message)
    class ContainsInvalidCharactersException(message: String) : IsInvalidException(message)

    open class IsIncorrectException(override val message: String) : RuntimeException()
    class LengthIsIncorrectException(message: String) : IsIncorrectException(message)
    class IsNumberException : IsIncorrectException("Username must not be a number")
    class StartsOrEndsWithPeriodException : IsIncorrectException("Username must not start or end with a period ('.')")
    class ContainsConsecutivePeriodsException : IsIncorrectException("Username must not contain two consecutive periods ('..')")
    class StartsOrEndsWithUnderscoreException : IsIncorrectException("Username must not start or end with an underscore ('_')")
    class ContainsConsecutiveUnderscoresException : IsIncorrectException("Username must not contain two consecutive underscores ('__')")
    class StartsWithWWWException : IsIncorrectException("Username must not start with \"www\"")
    class IsAlreadyTakenException : IsIncorrectException("Username is already taken")

    init {
        throwIfInvalid()
    }

    override fun throwIfInvalid(): Username {
        if(value.length !in 1..26)
            throw LengthIsInvalidException("Username must be between 1 and 26 characters long")
        if(!value.matches(Regex("^[a-z0-9._]+$")))
            throw ContainsInvalidCharactersException("Username must only contain lowercase letters, numbers, dots and underscores")

        return this
    }
}