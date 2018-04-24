package com.example.data.network.deserializer

import android.text.format.DateUtils
import com.google.gson.*
import org.joda.time.DateTime
import java.lang.reflect.Type

class UnixDateTimeDeserializer : JsonDeserializer<DateTime>, JsonSerializer<DateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): DateTime {
        return json.asJsonPrimitive.asString.toLong().asUnixSecondsToDateTime()
    }

    override fun serialize(src: DateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return context.serialize(src.toUnixTimeSeconds().toString())
    }

    private fun DateTime.toUnixTimeSeconds() = millis / DateUtils.SECOND_IN_MILLIS

    private fun Long.asUnixSecondsToDateTime() = DateTime(this * DateUtils.SECOND_IN_MILLIS)
}