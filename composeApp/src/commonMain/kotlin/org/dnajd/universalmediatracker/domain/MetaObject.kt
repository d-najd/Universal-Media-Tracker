package org.dnajd.universalmediatracker.domain

import kotlinx.serialization.Serializable

@Serializable
data class MetaObject(
    val id: String,
    val type: String,
    val name: String,
    val posterUrl: String,

    val description: String?
) {
    fun toMetaPreviewObject(): MetaPreviewObject {
        return MetaPreviewObject(
            id = id,
            type = type,
            name = name,
            posterUrl = posterUrl
        )
    }
}
