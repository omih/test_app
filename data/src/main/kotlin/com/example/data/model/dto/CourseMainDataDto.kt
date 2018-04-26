package com.example.data.model.dto

import com.google.gson.annotations.SerializedName

internal data class CourseMainDataDto(
        @SerializedName("id")
        val id: Long,
        @SerializedName("position")
        val position: Int,
        @SerializedName("score")
        val score: Double,
        @SerializedName("target_id")
        val targetId: Long,
        @SerializedName("target_type")
        val targetType: String,
        @SerializedName("course")
        val courseId: Long,
        @SerializedName("course_owner")
        val ownerId: Long,
        @SerializedName("course_authors")
        val authorsId: List<Long>,
        @SerializedName("course_title")
        val title: String,
        @SerializedName("course_slug")
        val slug: String,
        @SerializedName("course_cover")
        val logo: String?
)