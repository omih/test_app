package com.example.data.storage.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class UUIDListTypeConverter {

    @TypeConverter
    fun fromList(listOfStrings: List<UUID>?) = if( listOfStrings == null) null else Gson().toJson(listOfStrings)

    @TypeConverter
    fun toList(jsonString: String?): List<UUID>? = if( jsonString == null) null else Gson().fromJson(jsonString, object: TypeToken<List<UUID>>(){}.type)
}