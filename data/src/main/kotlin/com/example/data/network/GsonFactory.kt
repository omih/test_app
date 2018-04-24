package com.example.data.network

import com.example.data.network.deserializer.BooleanDeserializer
import com.example.data.network.deserializer.IsoDateTimeDeserializer
import com.example.data.network.deserializer.IsoLocalDateTimeDeserializer
import com.example.data.network.deserializer.ListDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.joda.time.DateTime
import org.joda.time.LocalDateTime

object GsonFactory {
    val networkGson: Gson by lazy {
        val gsonBuilder = GsonBuilder()
        //general
        gsonBuilder.registerTypeAdapter(java.lang.Boolean::class.java, BooleanDeserializer())
        gsonBuilder.registerTypeAdapter(Boolean::class.java, BooleanDeserializer())
        gsonBuilder.registerTypeAdapter(List::class.java, ListDeserializer())

        //project-related
        gsonBuilder.registerTypeAdapter(DateTime::class.java, IsoDateTimeDeserializer())
        gsonBuilder.registerTypeAdapter(LocalDateTime::class.java, IsoLocalDateTimeDeserializer())

        gsonBuilder.create()
    }
}