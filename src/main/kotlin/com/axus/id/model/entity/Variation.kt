package com.axus.id.model.entity

import com.axus.hypercdn.model.entity.Image
import com.axus.id.model.value.*
import eth.likespro.commons.models.Entity
import eth.likespro.commons.models.Validatable
import eth.likespro.commons.models.Value
import eth.likespro.commons.models.entity.CustomGeolocation
import eth.likespro.commons.models.value.Timestamp
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class Variation(
    override val id: Id = Id(),
    val auid: AUID,
    var icon: Image.Id? = null,
    var firstName: FirstName? = null,
    var lastName: LastName? = null,
    var status: Status? = null,
    var description: Description? = null,
    var location: CustomGeolocation? = null,
    val createdAt: Timestamp = Timestamp.now(),
): Entity<Variation.Id>, Validatable<Variation> {
    open class IsInvalidException(override val message: String) : RuntimeException()
    class FirstNameAndLastNameCombinedLengthIsInvalidException(message: String) : IsInvalidException(message)

    open class IsIncorrectException(override val message: String) : RuntimeException()
    class IsUnavailableException : IsIncorrectException("Variation with the specified Id is unavailable")

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
                throw LengthIsInvalidException("Variation.Id must be 36 characters long")

            return this
        }
    }

    override fun throwIfInvalid(): Variation = this.also {
        if((firstName?.value + lastName?.value).length > 28)
            throw FirstNameAndLastNameCombinedLengthIsInvalidException("First name and last name combined must not exceed 28 characters")
    }

    companion object {
        val EVERYONE = Variation(Id(), Credentials.EVERYONE.auid, null, FirstName("Everyone"), null, null, Description("Everyone in the  AXUS™ ID system, including You."))
        val LIKESPRO = Variation(Id(), Credentials.LIKESPRO.auid, null, FirstName("Like Pro"), null, Status("Deploying the AXUS™ ID..."), Description("Creator of AXUS™ and the AXUS™ ID system"))
        val AXUSID = Variation(Id(), Credentials.AXUSID.auid, null, FirstName("AXUS™ ID"), null, Status("Initializing.....ok"), Description("I AM THE SYSTEM. I AM THE ONE WHO KEEPS YOUR ACCOUNT RUNNING."))
        val AXUS = Variation(Id(), Credentials.AXUS.auid, null, FirstName("AXUS™"), null, Status("Beginning of a new era..."), Description("AXUS™ is a new and the first company founded by likespro, and with that the next chapter in history is started."))
        val SHADOW = Variation(Id(), Credentials.SHADOW.auid, null, FirstName("Shadow"), null, null, Description("Previously known as Shadow Gamer on YouTube."))

        fun generateDefaultVariation(auid: AUID): Variation {
            return when(auid) {
                Credentials.EVERYONE.auid -> EVERYONE
                Credentials.LIKESPRO.auid -> LIKESPRO
                Credentials.AXUSID.auid -> AXUSID
                Credentials.AXUS.auid -> AXUS
                Credentials.SHADOW.auid -> SHADOW
                else -> Variation(auid = auid)
            }
        }
    }
}