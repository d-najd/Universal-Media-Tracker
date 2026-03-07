package org.dnajd.universalmediatracker.domain

import kotlinx.serialization.Serializable

@Serializable
data class MetaPreviewObject(
    val id: String,
    val type: String,
    val name: String,
    val posterUrl: String
)
