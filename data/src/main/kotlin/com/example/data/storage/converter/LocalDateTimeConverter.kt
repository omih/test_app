package com.example.data.storage.converter

import android.arch.persistence.room.TypeConverter
import org.joda.time.LocalDateTime

class LocalDateTimeConverter {

    @TypeConverter
    fun fromDateTime(dateTime: LocalDateTime?) = dateTime?.toDateTime()?.millis

    @TypeConverter
    fun toDateTime(millis: Long?) = millis?.let { LocalDateTime(it) }

}