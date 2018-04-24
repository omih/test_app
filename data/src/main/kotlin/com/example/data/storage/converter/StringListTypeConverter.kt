package com.example.data.storage.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StringListTypeConverter {

    @TypeConverter
    fun fromList(listOfStrings: List<String>?) = if( listOfStrings == null) null else Gson().toJson(listOfStrings)

    @TypeConverter
    fun toList(jsonString: String?): List<String>? = if( jsonString == null) null else Gson().fromJson(jsonString, object: TypeToken<List<String>>(){}.type)
}