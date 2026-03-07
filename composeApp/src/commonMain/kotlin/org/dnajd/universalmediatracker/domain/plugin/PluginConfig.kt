package org.dnajd.universalmediatracker.domain.plugin

import kotlinx.serialization.Serializable
import org.dnajd.universalmediatracker.domain.MetaPreviewObject

@Serializable
class PluginBuilder(
    val id: String,
    val name: String,
    /**
     * Must be formatted XX.YY.ZZ
     * TODO add check
     */
    val version: String,
    val description: String?,
    val catalogHandlerSettings: PluginCatalogHandler?,
    // private val metadataHandlerSettings: PluginMetadataHandler?,
) {
    fun defineCatalogHandler(): PluginCatalogHandler {
        return PluginCatalogHandler()
    }
}
