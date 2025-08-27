package com.axus.id.model.value

import kotlinx.serialization.Serializable

@Serializable
data class PasswordHash(
    val value: String
): Hash {
    companion object {
        val EMPTY = PasswordHash("")
    }
}