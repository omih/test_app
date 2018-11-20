package com.example.data.network

import com.example.data.network.deserializer.BooleanDeserializer
import com.example.data.network.deserializer.IsoDateTimeDeserializer
import com.example.data.network.deserializer.IsoLocalDateTimeDeserializer
import com.example.data.network.deserializer.ListDeserializer
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import org.joda.time.DateTime
import org.joda.time.LocalDateTime

class ClientFactory {

    fun create() = HttpClient(Android) {
        install(JsonFeature) {
            serializer = GsonSerializer {
                registerTypeAdapter(java.lang.Boolean::class.java, BooleanDeserializer())
                registerTypeAdapter(Boolean::class.java, BooleanDeserializer())
                registerTypeAdapter(List::class.java, ListDeserializer())
                registerTypeAdapter(DateTime::class.java, IsoDateTimeDeserializer())
                registerTypeAdapter(LocalDateTime::class.java, IsoLocalDateTimeDeserializer())
            }
        }
    }
}