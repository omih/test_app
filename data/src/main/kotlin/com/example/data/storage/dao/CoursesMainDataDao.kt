package com.example.data.storage.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.example.data.model.entity.CourseMainDataEntity
import com.example.data.storage.Tables

@Dao
internal abstract class CoursesMainDataDao : BaseDao<CourseMainDataEntity> {
    @Query("DELETE FROM ${Tables.COURSE_MAIN_DATA}")
    internal abstract fun clear()

    @Query("SELECT * FROM ${Tables.COURSE_MAIN_DATA} WHERE title LIKE :searchString")
    internal abstract fun allCourses(searchString: String): List<CourseMainDataEntity>

    @Query("SELECT * FROM ${Tables.COURSE_MAIN_DATA} WHERE course_id = :courseId")
    internal abstract fun getCourse(courseId: Long): CourseMainDataEntity?

    @Query("SELECT course_id FROM ${Tables.COURSE_MAIN_DATA}")
    internal abstract fun getIdCourses(): List<Long>

    @Query("DELETE FROM ${Tables.COURSE_MAIN_DATA} WHERE course_id = :courseId")
    internal abstract fun deleteCourse(courseId: Long)

}