package com.example.data.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.data.model.entity.CourseMainDataEntity
import com.example.data.storage.converter.*
import com.example.data.storage.dao.CoursesMainDataDao

@Database(
    entities = [
        CourseMainDataEntity::class
    ], version = 1
)
@TypeConverters(
    BooleanTypesConverter::class,
    CommonTypesConverter::class,
    LocalDateTimeConverter::class,
    LongListTypeConverter::class,
    StringListTypeConverter::class,
    UUIDListTypeConverter::class
)
abstract class MainDatabase : RoomDatabase() {
    companion object {
        const val DB_NAME = "test_app_db"
    }

    internal abstract fun coursesMainDataDao(): CoursesMainDataDao

    fun clearDatabase() {
        beginTransaction()
        try {
            coursesMainDataDao().clear()

            query("VACUUM", null)

            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
    }
}