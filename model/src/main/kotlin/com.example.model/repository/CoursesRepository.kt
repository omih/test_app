package com.example.model.repository

import com.example.model.model.CourseMainData
import io.reactivex.Completable
import io.reactivex.Flowable

interface CoursesRepository {
    fun loadCoursesFromServer(searchString: String): Flowable<List<CourseMainData>>

    fun loadCoursesFavorite(searchString: String): Flowable<List<CourseMainData>>

    fun addToFavoriteCourse(courseMainData: CourseMainData): Completable
    fun removeFromFavorite(course: CourseMainData): Completable
}