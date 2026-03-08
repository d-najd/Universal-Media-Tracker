package org.dnajd.universalmediatracker.domain.plugin

import kotlinx.serialization.Serializable

@Serializable
class PluginSettings(
    val id: String,
    val name: String,
    /**
     * Must be formatted XX.YY.ZZ
     * TODO add check
     */
    val version: String,
    // val description: String? = null,
)
