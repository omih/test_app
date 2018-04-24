package com.example.data.network.deserializer

import com.google.gson.*
import org.joda.time.LocalDateTime
import java.lang.reflect.Type


class IsoLocalDateTimeDeserializer : JsonDeserializer<LocalDateTime>, JsonSerializer<LocalDateTime> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): LocalDateTime {
        return LocalDateTime(json.asJsonPrimitive.asString)
    }

    override fun serialize(src: LocalDateTime, typeOfSrc: Type, context: JsonSerializationContext): JsonElement {
        return context.serialize(src.toString())
    }
}