package com.example.data.network.api.response

import com.example.data.model.dto.CourseMainDataDto
import com.google.gson.annotations.SerializedName

data class CoursesMainDataResponse(
        @SerializedName("search-results")
        val courses: List<CourseMainDataDto>? = null
)