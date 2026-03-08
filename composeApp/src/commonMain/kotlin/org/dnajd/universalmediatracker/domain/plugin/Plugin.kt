package org.dnajd.universalmediatracker.domain.plugin

import kotlinx.serialization.Serializable

@Serializable
class Plugin(
    val settings: PluginSettings,
    // val catalogHandler: PluginCatalogHandler?,
)