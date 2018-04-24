package com.example.data.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type


class BooleanDeserializer : JsonDeserializer<Boolean> {
    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Boolean? {
        val result = getBooleanFromString(json.asString)

        return result?.let { it } ?: json.asBoolean
    }

    // 0 = false, 1 = true, null otherwise
    private fun getBooleanFromInt(i: Int): Boolean? {
        return when (i) {
            0 -> java.lang.Boolean.FALSE
            1 -> java.lang.Boolean.TRUE
            else -> null
        }
    }

    private fun getBooleanFromString(message: String?): Boolean? {
        if (message == null) {
            return null
        }

        if (message.equals("success", ignoreCase = true)) {
            return java.lang.Boolean.TRUE
        } else if (message.equals("fail", ignoreCase = true)) {
            return java.lang.Boolean.FALSE
        }

        if (message.equals("true", ignoreCase = true)) {
            return java.lang.Boolean.TRUE
        } else if (message.equals("false", ignoreCase = true)) {
            return java.lang.Boolean.FALSE
        }

        return null
    }
}