package com.example.model.usecase

import com.example.model.model.CourseMainData
import com.example.model.repository.CoursesRepository
import javax.inject.Inject

class CoursesUseCase @Inject constructor(private val repository: CoursesRepository) {

    fun loadCoursesFromServer() = repository.loadCoursesFromServer()

    fun loadFavoriteCourses() = repository.loadCoursesFavorite()

    fun addToFavorite(course: CourseMainData) = repository.addToFavoriteCourse(course)
    fun removeFromFavorite(course: CourseMainData) = repository.removeFromFavorite(course)
}