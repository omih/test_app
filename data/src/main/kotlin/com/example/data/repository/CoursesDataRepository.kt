package com.example.data.repository

import com.example.data.converter.CourseMainDataConverter
import com.example.data.network.api.Api
import com.example.data.storage.MainDatabase
import com.example.model.model.CourseMainData
import com.example.model.repository.CoursesRepository
import javax.inject.Inject

class CoursesDataRepository @Inject constructor(
    val api: Api,
    val db: MainDatabase
) : CoursesRepository {

    override fun removeFavoriteCourse(course: Long) {
        db.coursesMainDataDao().deleteCourse(course)
    }

    override fun addToFavoriteCourse(course: CourseMainData) {
        db.coursesMainDataDao().insert(CourseMainDataConverter.toEntity(course))
    }

    override suspend fun loadCoursesFromServer(searchString: String): List<CourseMainData> {
        return api.searchCourses(query = searchString)
            .await()
            .courses
            .orEmpty()
            .map { CourseMainDataConverter.toModel(it) }
    }

    override fun courseIsFavorite(courseId: Long): Boolean {
        return db.coursesMainDataDao().getCourse(courseId) != null
    }

    override fun coursesFromLocal(searchString: String): List<CourseMainData> {
        val localCourses = db.coursesMainDataDao().allCourses("%$searchString%")
        return localCourses.map { CourseMainDataConverter.toModel(it) }
    }

    override fun favoriteCoursesId(): List<Long> {
        return db.coursesMainDataDao().getIdCourses()
    }

}