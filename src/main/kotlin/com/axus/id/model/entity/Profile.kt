package com.axus.id.model.entity

import com.axus.hypercdn.model.entity.Image
import com.axus.id.model.value.AUID
import com.axus.id.model.value.Username
import eth.likespro.commons.models.Entity
import eth.likespro.commons.models.value.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val auid: AUID,
    var avatar: Image.Id? = null,
    var icon: Image.Id? = null,
    var verifiedUsername: Username? = null,
    val createdAt: Timestamp = Timestamp.now(),
): Entity<AUID> {
    override val id get() = auid

    companion object {
        val EVERYONE = Profile(AUID(0), null, null, null)
        val LIKESPRO = Profile(AUID(1), null, null, Credentials.LIKESPRO.username)
        val AXUSID = Profile(AUID(2), null, null, Credentials.AXUSID.username)
        val AXUS = Profile(AUID(3), null, null, Credentials.AXUS.username)
        val SHADOW = Profile(AUID(4), null, null, Credentials.SHADOW.username)
    }
}