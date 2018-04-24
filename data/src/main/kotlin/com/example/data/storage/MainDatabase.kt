package com.example.data.storage

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.example.data.model.entity.CourseFullDataEntity
import com.example.data.model.entity.CourseMainDataEntity
import com.example.data.model.entity.UserEntity
import com.example.data.storage.converter.*
import com.example.data.storage.dao.CoursesFullDataDao
import com.example.data.storage.dao.CoursesMainDataDao
import com.example.data.storage.dao.UserDao

@Database(entities = [
    CourseFullDataEntity::class,
    CourseMainDataEntity::class,
    UserEntity::class
], version = 1)
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

    abstract fun coursesFullDataDao(): CoursesFullDataDao
    abstract fun coursesMainDataDao(): CoursesMainDataDao
    abstract fun userDao(): UserDao

    fun clearDatabase() {
        beginTransaction()
        try {
            coursesFullDataDao().clear()
            coursesMainDataDao().clear()
            userDao().clear()

            query("VACUUM", null)

            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
    }
}