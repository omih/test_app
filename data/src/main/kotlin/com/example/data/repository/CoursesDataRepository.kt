package com.example.data.repository

import com.example.data.converter.CourseMainDataConverter
import com.example.data.extension.schedulersIoToMain
import com.example.data.network.api.Api
import com.example.data.scheduler.SchedulerProvider
import com.example.data.storage.MainDatabase
import com.example.model.model.CourseMainData
import com.example.model.repository.CoursesRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class CoursesDataRepository @Inject constructor(val api: Api, val db: MainDatabase, val schedulers: SchedulerProvider) : CoursesRepository {
    override fun loadCoursesFavorite(searchString: String): Flowable<List<CourseMainData>> {
        return loadCoursesFavoriteFromDb(searchString)
                .schedulersIoToMain(schedulers)
    }

    private fun loadCoursesFavoriteFromDb(searchString: String): Flowable<List<CourseMainData>> {
        return db.coursesMainDataDao()
                .allCourses()
                .flattenAsFlowable { it }
                .map { CourseMainDataConverter.toModel(it) }
                .filter { filterCourseByString(searchString, it) }
                .toList()
                .toFlowable()
    }

    private fun filterCourseByString(searchString: String, it: CourseMainData): Boolean {
        return when {
            searchString.isNotEmpty() -> it.title.contains(searchString, true)
            else -> true
        }
    }

    override fun removeFromFavorite(course: CourseMainData): Completable {
        return Completable.fromCallable {
            db.coursesMainDataDao()
                    .delete(CourseMainDataConverter.toEntity(course))
        }.schedulersIoToMain(schedulers)
    }

    override fun addToFavoriteCourse(courseMainData: CourseMainData): Completable {
        return Completable.fromCallable {
            db.coursesMainDataDao()
                    .insert(CourseMainDataConverter.toEntity(courseMainData))
        }.schedulersIoToMain(schedulers)
    }

    override fun loadCoursesFromServer(searchString: String): Flowable<List<CourseMainData>> {
        return loadCoursesFavoriteFromDb(searchString)
                .mergeWith(loadCourseFromServerImpl(searchString))
                .flatMapIterable { it }
                .distinct { it.courseId }
                .toList()
                .toFlowable()
                .schedulersIoToMain(schedulers)
    }

    private fun loadCourseFromServerImpl(searchString: String): Flowable<List<CourseMainData>> {
        return api.searchCourses(query = searchString)
                .map { it.courses }
                .flatMapIterable { it }
                .map { CourseMainDataConverter.toModel(it) }
                .toList()
                .toFlowable()
    }
}