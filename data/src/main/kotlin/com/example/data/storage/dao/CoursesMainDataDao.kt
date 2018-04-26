package com.example.data.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.data.model.entity.CourseMainDataEntity
import com.example.data.storage.Tables
import io.reactivex.Maybe

@Dao
internal abstract class CoursesMainDataDao : BaseDao<CourseMainDataEntity> {
    @Query("DELETE FROM ${Tables.COURSE_MAIN_DATA}")
    internal abstract fun clear()

    @Query("SELECT * FROM ${Tables.COURSE_MAIN_DATA}")
    internal abstract fun allCourses(): Maybe<List<CourseMainDataEntity>>

}