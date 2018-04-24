package com.example.data.network.deserializer

import com.google.gson.*
import org.joda.time.DateTime
import java.lang.reflect.Type


class IsoDateTimeDeserializer : JsonDeserializer<DateTime>, JsonSerializer<DateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): DateTime {
        return DateTime(json.asJsonPrimitive.asString)
    }

    override fun serialize(src: DateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return context.serialize(src.toLocalDateTime().toString())
    }
}