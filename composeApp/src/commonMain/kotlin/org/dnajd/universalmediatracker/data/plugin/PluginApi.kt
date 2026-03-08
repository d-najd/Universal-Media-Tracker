package org.dnajd.universalmediatracker.data.plugin

import io.ktor.http.ContentDisposition.Companion.File
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.dnajd.universalmediatracker.PluginApiTesting
import org.dnajd.universalmediatracker.domain.plugin.Plugin
import org.dnajd.universalmediatracker.util.JavascriptParser

interface PluginApi {

}

class JavascriptPluginApi: PluginApi, AutoCloseable {
    val parser = JavascriptParser()

    init {
        CoroutineScope(Dispatchers.Main).launch {
            parser.evaluate<Unit>(PluginApiTesting.code)
        }
    }

    fun parseConfig() {
        CoroutineScope(Dispatchers.Main).launch {
            val first = parser.evaluate<Long>("testInt()")

            val result2 = parser.evaluate<Plugin>("build()")
            val final = ""
        }
    }

    override fun close() {
        parser.close()
    }
}