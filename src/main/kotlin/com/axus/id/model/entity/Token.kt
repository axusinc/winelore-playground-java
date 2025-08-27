package com.axus.id.model.entity

import eth.likespro.commons.models.Entity
import eth.likespro.commons.models.value.Timestamp
import eth.likespro.commons.models.Validatable
import eth.likespro.commons.numeric.Base64Utils.randomBase64
import kotlinx.serialization.Serializable

@Serializable
data class Token(
    override val id: Id = Id(randomBase64(64, true)),
    var refreshTokenId: RefreshToken.Id?,
    val createdAt: Timestamp = Timestamp.now(),
    val expiresAt: Timestamp = Timestamp.now() + 12L * 3600 * 1000 /* 12 hours */
): Entity<Token.Id>, Validatable<Token> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class IsExpiredException : IsInvalidException("Token is expired")

    @Serializable
    data class Id(val value: String): Validatable<Id> {
        open class IsInvalidException(override val message: String) : RuntimeException()
        class LengthIsInvalidException(message: String) : IsInvalidException(message)

        init {
            throwIfInvalid()
        }

        override fun throwIfInvalid(): Id {
            if(value.length != 64)
                throw LengthIsInvalidException("Token.Id must be 64 characters long")

            return this
        }
    }

    val isExpired: Boolean
        get() = expiresAt < Timestamp.now()

    override fun throwIfInvalid(): Token {
        if(isExpired)
            throw IsExpiredException()

        return this
    }

    fun eraseRefreshTokenId() = this.apply { refreshTokenId = null }
}