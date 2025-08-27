package com.axus.hypercdn.model.entity

import eth.likespro.commons.models.Entity
import eth.likespro.commons.models.Value
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    override val id: Id
): Entity<Image.Id> {
    @Serializable
    data class Id(val value: String): Value
}