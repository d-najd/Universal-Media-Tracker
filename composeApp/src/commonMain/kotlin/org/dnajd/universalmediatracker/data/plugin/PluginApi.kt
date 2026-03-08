package org.dnajd.universalmediatracker.data.plugin

import org.dnajd.universalmediatracker.util.JavascriptParser

interface PluginApi {

}

class JavascriptPluginApi: PluginApi, AutoCloseable {
    val parser = JavascriptParser()

    override fun close() {
        parser.close()
    }
}