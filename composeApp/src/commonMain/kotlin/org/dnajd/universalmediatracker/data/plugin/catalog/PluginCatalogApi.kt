package org.dnajd.universalmediatracker.data.plugin.catalog

import org.dnajd.universalmediatracker.domain.MetaPreviewObject
import org.dnajd.universalmediatracker.domain.plugin.PluginCatalogRequest
import org.dnajd.universalmediatracker.util.JavascriptParser

interface PluginCatalogApi {
    suspend fun request(extras: PluginCatalogRequest): List<MetaPreviewObject>
}

class JavascriptPluginCatalogApi: PluginCatalogApi, AutoCloseable {
    val parser = JavascriptParser()

    override suspend fun request(extras: PluginCatalogRequest): List<MetaPreviewObject> {
        TODO()
    }

    override fun close() {
        parser.close()
    }
}