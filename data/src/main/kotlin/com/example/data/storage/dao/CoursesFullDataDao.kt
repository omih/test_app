package com.example.data.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.data.model.entity.CourseFullDataEntity
import com.example.data.storage.Tables

@Dao
abstract class CoursesFullDataDao : BaseDao<CourseFullDataEntity> {
    @Query("DELETE FROM ${Tables.COURSE_FULL_DATA}")
    abstract fun clear()

}