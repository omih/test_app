package com.example.data.repository

import com.example.data.converter.CourseMainDataConverter
import com.example.data.extension.schedulersIoToMain
import com.example.data.network.api.Api
import com.example.data.storage.MainDatabase
import com.example.model.model.CourseMainData
import com.example.model.repository.CoursesRepository
import io.reactivex.Single
import javax.inject.Inject

class CoursesDataRepository @Inject constructor(val api: Api, val db: MainDatabase) : CoursesRepository {
    override fun loadCoursesMainData(): Single<MutableList<CourseMainData>> {
        return api.searchCourses()
                .map { it.courses }
                .flattenAsFlowable { it }
                .map { CourseMainDataConverter.toModel(it) }
                .toList()
                .schedulersIoToMain()
    }
}