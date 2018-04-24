
package com.example.data.storage.converter

import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LongListTypeConverter {

    @TypeConverter
    fun fromList(listOfStrings: List<Long>) = Gson().toJson(listOfStrings)

    @TypeConverter
    fun toList(jsonString: String): List<Long> = Gson().fromJson(jsonString, object: TypeToken<List<Long>>(){}.type)
}