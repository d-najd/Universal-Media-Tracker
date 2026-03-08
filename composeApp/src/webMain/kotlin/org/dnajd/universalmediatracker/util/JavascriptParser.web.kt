package org.dnajd.universalmediatracker.util

actual class JavascriptParser : AutoCloseable {
    actual fun compile(code: String, filename: String, asModule: Boolean) {
    }

    actual suspend inline fun <reified T> evaluate(
        code: String,
        filename: String,
        asModule: Boolean
    ): T {
        TODO("Not yet implemented")
    }

    actual override fun close() {
    }
}