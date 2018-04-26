package com.example.data.repository

import com.example.data.converter.CourseMainDataConverter
import com.example.data.extension.schedulersIoToMain
import com.example.data.network.api.Api
import com.example.data.storage.MainDatabase
import com.example.model.model.CourseMainData
import com.example.model.repository.CoursesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CoursesDataRepository @Inject constructor(val api: Api, val db: MainDatabase) : CoursesRepository {
    override fun loadCoursesFavorite(): Flowable<List<CourseMainData>> {
        return loadCoursesFavoriteFromDb()
                .schedulersIoToMain()
    }

    private fun loadCoursesFavoriteFromDb(): Flowable<List<CourseMainData>> {
        return db.coursesMainDataDao()
                .allCourses()
                .flattenAsFlowable { it }
                .map { CourseMainDataConverter.toModel(it) }
                .toList()
                .toFlowable()
    }

    override fun removeFromFavorite(course: CourseMainData): Completable {
        return Completable.fromCallable {
            db.coursesMainDataDao()
                    .delete(CourseMainDataConverter.toEntity(course))
        }.schedulersIoToMain()
    }

    override fun addToFavoriteCourse(courseMainData: CourseMainData): Completable {
        return Completable.fromCallable {
            db.coursesMainDataDao()
                    .insert(CourseMainDataConverter.toEntity(courseMainData))
        }.schedulersIoToMain()
    }

    override fun loadCoursesFromServer(): Flowable<List<CourseMainData>> {
        return loadCoursesFavoriteFromDb()
                .mergeWith(loadCourseFromServerImpl())
                .flatMapIterable { it }
                .distinct { it.courseId }
                .toList()
                .toFlowable()
                .schedulersIoToMain()
    }

    private fun loadCourseFromServerImpl(): Flowable<List<CourseMainData>> {
        return api.searchCourses()
                .map { it.courses }
                .flatMapIterable { it }
                .map { CourseMainDataConverter.toModel(it) }
                .toList()
                .toFlowable()
    }
}