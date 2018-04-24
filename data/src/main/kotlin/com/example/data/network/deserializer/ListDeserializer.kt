package com.example.data.network.deserializer

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import timber.log.Timber
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.util.*

/**
 * @author maximefimov
 */
class ListDeserializer : JsonDeserializer<List<*>> {

    companion object {
        val TAG = "ListDeserializer"
    }

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, type: Type,
                             context: JsonDeserializationContext): List<*> {

        val jsonArray = json.asJsonArray
        val result = ArrayList<Any>(jsonArray.size())
        val elementType = (type as ParameterizedType).actualTypeArguments[0]

        for (jsonElement in jsonArray) {
            var deserializedElement: Any? = null
            try {
                deserializedElement = context.deserialize<Any>(jsonElement, elementType)
            } catch (e: JsonParseException) {
                Timber.tag(TAG).e(e)
            }

            if (deserializedElement != null) {
                result.add(deserializedElement)
            }
        }

        return result
    }
}