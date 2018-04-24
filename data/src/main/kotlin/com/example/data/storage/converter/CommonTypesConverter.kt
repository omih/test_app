package com.example.data.storage.converter

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime
import java.util.*


class CommonTypesConverter {
    @TypeConverter
    fun fromDateTime(dateTime: DateTime?): Long = if (dateTime == null) 0L else dateTime.millis

    /*@TypeConverter
    fun fromNullableDateTime(dateTime: DateTime?): Long? = if( dateTime == null ) null else dateTime.millis*/

    @TypeConverter
    fun toDateTime(millis: Long): DateTime? = if (millis != 0L) DateTime(millis) else null

    @TypeConverter
    fun fromUUID(uuid: UUID?) = uuid?.toString()

    @TypeConverter
    fun toUUID(uuid: String?) = uuid?.let { UUID.fromString(uuid) }
}