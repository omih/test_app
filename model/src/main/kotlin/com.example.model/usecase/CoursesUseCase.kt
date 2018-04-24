package com.example.model.usecase

import com.example.model.repository.CoursesRepository
import javax.inject.Inject

class CoursesUseCase @Inject constructor(val repository: CoursesRepository) {

    fun getCoursesMainData() = repository.loadCoursesMainData()
}