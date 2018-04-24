package com.example.model.repository

import com.example.model.model.CourseMainData
import io.reactivex.Single

interface CoursesRepository {
    fun loadCoursesMainData(): Single<MutableList<CourseMainData>>
}