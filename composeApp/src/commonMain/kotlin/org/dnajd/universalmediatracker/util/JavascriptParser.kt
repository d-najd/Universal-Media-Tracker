package org.dnajd.universalmediatracker.util

import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

expect class JavascriptParser: AutoCloseable {
    constructor()
    fun compile(code: String, filename: String = "main.js", asModule: Boolean = false)
    suspend inline fun <reified T> evaluate(code: String, filename: String = "main.js", asModule: Boolean = false): T
    override fun close()
}