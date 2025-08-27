package com.axus.id.model.entity

import eth.likespro.commons.models.Entity
import com.axus.id.model.value.AUID
import com.axus.id.model.value.PasswordHash
import com.axus.id.model.value.Username
import eth.likespro.commons.models.value.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class Credentials(
    val auid: AUID,
    var username: Username,
    var passwordHash: PasswordHash?,
    val createdAt: Timestamp = Timestamp.now()
): Entity<AUID> {
    open class IsIncorrectException(override val message: String) : RuntimeException()
    class AUIDOrPasswordIsIncorrectException : IsIncorrectException("AUID or password is incorrect")

    override val id get() = auid

    companion object {
        val EVERYONE = Credentials(AUID(0), Username("everyone"), PasswordHash("$2a$12\$UrVlEFjd9ifos.qouFh.KOvSA4xq6esggH/ECgVkTiVuJ1Qs8EDHe"))
        val LIKESPRO = Credentials(AUID(1), Username("likespro"), PasswordHash("$2a$12\$UrVlEFjd9ifos.qouFh.KOvSA4xq6esggH/ECgVkTiVuJ1Qs8EDHe"))
        val AXUSID = Credentials(AUID(2), Username("axusid"), PasswordHash("$2a$12\$UrVlEFjd9ifos.qouFh.KOvSA4xq6esggH/ECgVkTiVuJ1Qs8EDHe"))
        val AXUS = Credentials(AUID(3), Username("axus"), PasswordHash("$2a$12\$UrVlEFjd9ifos.qouFh.KOvSA4xq6esggH/ECgVkTiVuJ1Qs8EDHe"))
        val SHADOW = Credentials(AUID(4), Username("shadow"), PasswordHash("$2a$12\$UrVlEFjd9ifos.qouFh.KOvSA4xq6esggH/ECgVkTiVuJ1Qs8EDHe"))
    }

    fun erasePasswordHash() = this.apply { passwordHash = null }
}