package com.example.data.network.api.response

import com.example.data.model.dto.CourseMainDataDto
import com.google.gson.annotations.SerializedName

data class CoursesMainDataResponse internal constructor(
        @SerializedName("meta")
        val meta: Meta,
        @SerializedName("search-results")
        val courses: List<CourseMainDataDto>? = null
)