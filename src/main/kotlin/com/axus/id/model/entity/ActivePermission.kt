package com.axus.id.model.entity

import com.axus.id.model.value.PermissionPattern
import eth.likespro.commons.models.value.Timestamp
import com.axus.id.model.value.AUID
import eth.likespro.commons.models.Entity
import eth.likespro.commons.models.Validatable
import eth.likespro.commons.models.Value
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class ActivePermission(
    override val id: Id = Id(),
    val from: AUID,
    val to: AUID,
    val context: AUID,
    val permissionPattern: PermissionPattern,
    val createdAt: Timestamp = Timestamp.now(),
    val expiresAt: Timestamp = Timestamp.NEVER
): Entity<ActivePermission.Id>, Validatable<ActivePermission> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class IsExpiredException : IsInvalidException("ActivePermission is expired")

    @Serializable
    data class Id(
        val value: String = UUID.randomUUID().toString()
    ) : Value, Validatable<Id> {
        open class IsInvalidException(override val message: String) : RuntimeException()
        class LengthIsInvalidException(message: String) : IsInvalidException(message)

        init {
            throwIfInvalid()
        }

        override fun throwIfInvalid(): Id {
            if(value.length != 36)
                throw LengthIsInvalidException("ActivePermission.Id must be 36 characters long")

            return this
        }
    }

    val isExpired: Boolean
        get() = expiresAt < Timestamp.now()

    override fun throwIfInvalid(): ActivePermission {
        if(isExpired)
            throw IsExpiredException()

        return this
    }
}