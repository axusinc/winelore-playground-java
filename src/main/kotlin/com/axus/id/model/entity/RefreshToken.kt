package com.axus.id.model.entity

import eth.likespro.commons.models.Entity
import com.axus.id.model.value.AUID
import eth.likespro.commons.models.value.Timestamp
import eth.likespro.commons.models.Validatable
import eth.likespro.commons.numeric.Base64Utils.randomBase64
import kotlinx.serialization.Serializable

@Serializable
data class RefreshToken(
    override val id: Id = Id(randomBase64(128, true)),
    val auid: AUID,
    val createdAt: Timestamp = Timestamp.now(),
    val expiresAt: Timestamp = Timestamp.now() + 6L * 28 * 24 * 3600 * 1000 /* 6 months */
): Entity<RefreshToken.Id>, Validatable<RefreshToken> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class IsExpiredException : IsInvalidException("RefreshToken is expired")

    @Serializable
    data class Id(val value: String): Validatable<Id> {
        open class IsInvalidException(override val message: String) : RuntimeException()
        class LengthIsInvalidException(message: String) : IsInvalidException(message)

        init {
            throwIfInvalid()
        }

        override fun throwIfInvalid(): Id {
            if(value.length != 128)
                throw LengthIsInvalidException("RefreshToken.Id must be 128 characters long")

            return this
        }
    }

    val isExpired: Boolean
        get() = expiresAt < Timestamp.now()

    override fun validatedOrNull(): RefreshToken? {
        if(isExpired) return null
        return this
    }

    override fun throwIfInvalid(): RefreshToken {
        if(isExpired)
            throw IsExpiredException()

        return this
    }
}