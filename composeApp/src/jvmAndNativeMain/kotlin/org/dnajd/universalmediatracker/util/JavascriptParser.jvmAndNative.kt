package org.dnajd.universalmediatracker.util

import com.dokar.quickjs.QuickJs
import com.dokar.quickjs.binding.JsObject
import com.dokar.quickjs.conveter.SerializableConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.reflect.KClass

actual class JavascriptParser : AutoCloseable {
    val context: QuickJs = QuickJs.create(Dispatchers.Main)
    val registeredConverters = mutableSetOf<KClass<*>>()
    companion object {
        @OptIn(ExperimentalUnsignedTypes::class)
        val alreadyImplementedConverters = setOf(
            Int::class, Long::class, Short::class, Double::class, Float::class,
            String::class,
            Error::class,
            JsObject::class,
            ByteArray::class,
            UByteArray::class,
            Boolean::class,
        )
    }

    init {
        // Already supported by default
        registeredConverters.add(Int::class)
        registeredConverters.add(Long::class)
        registeredConverters.add(String::class)
        registeredConverters.add(Long::class)
    }

    actual fun compile(code: String, filename: String, asModule: Boolean) {
        context.compile(code, filename, asModule)
    }

    @OptIn(ExperimentalSerializationApi::class)
    actual suspend inline fun <reified T> evaluate(code: String, filename: String, asModule: Boolean): T {
        if (!alreadyImplementedConverters.contains(T::class) && !registeredConverters.contains(T::class)) {
            context.addTypeConverters(SerializableConverter<T>())
            registeredConverters.add(T::class)
        }

        return context.evaluate<T>(code.trimIndent(), filename, asModule)
    }

    actual override fun close() {
        context.close()
    }
}