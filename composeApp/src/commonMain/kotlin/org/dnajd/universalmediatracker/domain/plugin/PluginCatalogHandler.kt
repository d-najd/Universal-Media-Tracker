package org.dnajd.universalmediatracker.domain.plugin

import kotlinx.serialization.Serializable
import org.dnajd.universalmediatracker.domain.MetaPreviewObject

@Serializable
class PluginCatalogHandler(
    val settings: PluginCatalogSettings = PluginCatalogSettings(),
) {
    fun request(extras: PluginCatalogRequest): List<MetaPreviewObject> {
        TODO()
    }
}

@Serializable
class PluginCatalogSettings(
    val maxPageSize: Int = 100
)

@Serializable
class PluginCatalogRequest(
    val maxPageSize: Int = 100
)

