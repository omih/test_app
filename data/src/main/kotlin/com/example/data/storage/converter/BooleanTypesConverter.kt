package com.example.data.storage.converter

import android.arch.persistence.room.TypeConverter

class BooleanTypesConverter {
    @TypeConverter
    fun fromBoolean(boolean: Boolean) = if (boolean) 1 else 0

    @TypeConverter
    fun toBoolean(value: Int) = value == 1
}