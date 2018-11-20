package com.example.model.usecase

import com.example.model.model.CourseMainData
import com.example.model.repository.CoursesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import javax.inject.Inject

class CoursesUseCase
@Inject constructor(private val repository: CoursesRepository) {

    fun loadFavoriteCourses(searchString: String) =
        GlobalScope.async {
            repository.coursesFromLocal(searchString)
        }

    fun addToFavorite(course: CourseMainData) =
        GlobalScope.async {
            repository.addToFavoriteCourse(course)
        }

    fun removeFromFavorite(course: CourseMainData) =
        GlobalScope.async {
            repository.removeFavoriteCourse(course.courseId)
        }

    fun loadCoursesFromServer(searchString: String) =
        GlobalScope.async {
            val coursesFromServer = repository.loadCoursesFromServer(searchString)
            val idFavoriteCourses = repository.favoriteCoursesId()

            coursesFromServer.map {
                if (idFavoriteCourses.contains(it.courseId)) {
                    courseIsFavorite(it)
                } else {
                    it
                }
            }
        }

    private fun courseIsFavorite(course: CourseMainData): CourseMainData {
        return course.copy(favorite = true)
    }
}

