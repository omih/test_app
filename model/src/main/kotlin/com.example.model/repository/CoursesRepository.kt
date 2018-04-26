package com.example.model.repository

import com.example.model.model.CourseMainData
import io.reactivex.Completable
import io.reactivex.Flowable

interface CoursesRepository {
    fun loadCoursesFromServer(): Flowable<List<CourseMainData>>

    fun loadCoursesFavorite(): Flowable<List<CourseMainData>>

    fun addToFavoriteCourse(courseMainData: CourseMainData): Completable
    fun removeFromFavorite(course: CourseMainData): Completable
}