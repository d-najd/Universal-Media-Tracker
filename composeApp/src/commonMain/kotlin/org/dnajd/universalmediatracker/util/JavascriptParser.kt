package org.dnajd.universalmediatracker.util

import com.dokar.quickjs.QuickJs
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

interface JavascriptParser {
    fun parse(code: String);
}

@OptIn(ExperimentalSerializationApi::class)
class AppJavascriptParser: AutoCloseable {
    var context: QuickJs = QuickJs.create(Dispatchers.Main)

    init {
        // context.addTypeConverters(SerializableConverter<Teme>())
    }

    fun compile(code: String, filename: String = "main.js", asModule: Boolean = false) {
        context.compile(code, filename, asModule)
    }

    suspend inline fun <reified T> evaluate(code: String, filename: String = "main.js", asModule: Boolean = false): T {
        // val result = context.evaluate<T>(code.trimIndent(), filename, asModule)
        // return result

        return when (T::class) {
            Unit::class -> {
                context.evaluate<Unit>(code.trimIndent(), filename, asModule)
            }

            Int::class -> {
                val te = context.evaluate<Int>(code.trimIndent(), filename, asModule) as T
                val me = te as T
                return me
            }

            else -> {
                throw IllegalStateException()
            }
        } as T

        /*
        return when (T::class) {
            Unit::class -> Unit as T
            Int::class -> {
                context.close()
                val te = result
                val me = result as Long
                val ge = me as T
                return me as T
            }
            Boolean::class -> result as T
            String::class -> Json.decodeFromString(result as String)
            else -> {
                throw IllegalStateException("huh?")
            }
        }
         */
    }

    override fun close() {
        context.close()
    }
}


@Serializable
data class Teme(
    val first: Int,
)

class WebJavascriptParser {

}