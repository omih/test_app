package com.example.model.usecase

import com.example.model.model.CourseMainData
import com.example.model.repository.CoursesRepository
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class CoursesUseCase
@Inject constructor(private val repository: CoursesRepository) {

    fun loadFavoriteCourses(searchString: String) =
        async {
            repository.coursesFromLocal(searchString)
        }

    fun addToFavorite(course: CourseMainData) =
        async {
            repository.addToFavoriteCourseX(course)
        }

    fun removeFromFavorite(course: CourseMainData) =
        async {
            repository.removeFavoriteCourseX(course.courseId)
        }

    fun loadCoursesFromServer(searchString: String) =
        async {
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

