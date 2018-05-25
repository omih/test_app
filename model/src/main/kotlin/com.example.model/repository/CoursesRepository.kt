package com.example.model.repository

import com.example.model.model.CourseMainData

interface CoursesRepository {


    suspend fun loadCoursesFromServer(searchString: String): List<CourseMainData>
    fun courseIsFavorite(courseId: Long): Boolean
    fun removeFavoriteCourseX(course: Long)
    fun addToFavoriteCourseX(course: CourseMainData)
    fun coursesFromLocal(searchString: String): List<CourseMainData>
    fun favoriteCoursesId(): List<Long>
}