package org.dnajd.universalmediatracker.util

import androidx.compose.runtime.internal.composableLambda
import com.dokar.quickjs.QuickJs
import com.dokar.quickjs.binding.JsObject
import com.dokar.quickjs.binding.toJsObject
import com.dokar.quickjs.converter.JsObjectConverter
import com.dokar.quickjs.conveter.SerializableConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.io.decodeFromSource
import kotlinx.serialization.json.put
import org.dnajd.universalmediatracker.domain.plugin.Plugin
import org.dnajd.universalmediatracker.domain.plugin.PluginSettings
import kotlin.reflect.KClass

@OptIn(ExperimentalSerializationApi::class)
actual class JavascriptParser : AutoCloseable {
    val context: QuickJs = QuickJs.create(Dispatchers.Main)
    val registeredConverters = mutableSetOf<KClass<*>>()
    companion object {
        @OptIn(ExperimentalUnsignedTypes::class)
        val alreadyImplementedConverters = setOf(
            Int::class, Long::class, Short::class, Double::class, Float::class, Number::class,
            String::class,
            Unit::class,
            // Error::class,
            // ByteArray::class,
            // UByteArray::class,
            Boolean::class,
        )
    }

    init {
        context.addTypeConverters(SerializableConverter<PluginSettings>())
    }

    actual fun compile(code: String, filename: String, asModule: Boolean) {
        context.compile(code, filename, asModule)
    }

    @OptIn(ExperimentalSerializationApi::class)
    actual suspend inline fun <reified T> evaluate(code: String, filename: String, asModule: Boolean): T {
        if (alreadyImplementedConverters.contains(T::class)) {
            return context.evaluate<T>(code.trimIndent())
        }

        val result = context.evaluate<JsObject>(code.trimIndent()).to<T>();
        return result
    }

    actual override fun close() {
        context.close()
    }

    inline fun <reified T> JsObject.to(): T {
        return Json.decodeFromJsonElement(this.toJsonElement())
    }

    fun Any?.toJsonElement(): JsonElement = when (this) {
        null -> JsonNull
        is String -> JsonPrimitive(this)
        is Boolean -> JsonPrimitive(this)
        is Byte, is Short, is Int, is Long, is Float, is Double -> JsonPrimitive(this as Number)
        is Map<*, *> -> buildJsonObject {
            this@toJsonElement.forEach { (k, v) ->
                val key = k.toString() // keys must be strings in JSON
                put(key, v.toJsonElement())
            }
        }
        is Set<*> -> buildJsonArray {
            this@toJsonElement.forEach { add(it.toJsonElement()) }
        }
        is List<*> -> buildJsonArray {
            this@toJsonElement.forEach { add(it.toJsonElement()) }
        }
        is ByteArray -> buildJsonArray { this@toJsonElement.forEach { add(it.toInt()) } }
        is UByteArray -> buildJsonArray { this@toJsonElement.forEach { add(it.toInt()) } }
        /*
        is JsObject -> buildJsonObject {
            for (key in js("Object").keys(this@toJsonElement)) {
                val value = this@toJsonElement[key]
                put(key as String, value.toJsonElement())
            }
        }
         */
        is Throwable -> buildJsonObject {
            put("name", this@toJsonElement::class.simpleName ?: "Error")
            put("message", this@toJsonElement.message ?: "")
        }
        else -> JsonPrimitive(this.toString())
    }
}