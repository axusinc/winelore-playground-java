package com.axus.id.model.value

import eth.likespro.commons.models.Value
import eth.likespro.commons.models.Validatable
import io.ktor.util.*
import kotlinx.serialization.Serializable
import kotlin.text.isLowerCase

@Serializable
data class Permission(
    val value: String
): Value, Validatable<Permission> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class LengthIsInvalidException(message: String) : IsInvalidException(message)
    class ContainsDoubleColonException : IsInvalidException("Permission must not contain double colon")
    class StartsOrEndsWithColonException : IsInvalidException("Permission must not start or end with colon")
    class ContainsInvalidCharactersException(message: String) : IsInvalidException(message)
    class ContainsTripleStarException : IsInvalidException("Permission must not contain triple star")

    init {
        throwIfInvalid()
    }

    override fun throwIfInvalid(): Permission {
        if(value.length !in 1..128)
            throw LengthIsInvalidException("Permission must be between 1 and 128 characters long")
        if(value.contains("::"))
            throw ContainsDoubleColonException()
        if(value.startsWith(':') || value.endsWith(':'))
            throw StartsOrEndsWithColonException()
//        if(!Regex("""^[a-z0-9*](?:[a-z0-9:*]*[a-z0-9*]|\*)$""").matches(value))
//            throw PermissionIsInvalidException("Permission must match pattern " + """^[a-z0-9](?:[a-z0-9:*]*[a-z0-9*]|\*)$""")
        if(value.any { !it.isLetterOrDigit() && it != '-' && it != '_' && it != '*' && it != ':' && it != '.' })
            throw ContainsInvalidCharactersException("Permission must only contain letters, digits, '-', '_', '*', ':' and '.'")
        if(value.contains("***"))
            throw ContainsTripleStarException()

        return this
    }

    fun toPermissionPattern() = PermissionPattern(
        "^" + Regex.escape(value) +"$"
    )
}