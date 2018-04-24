package com.example.model.model

data class CourseMainData(
        val id: Long,
        val position: Int,
        val score: Double,
        val targetId: Long,
        val targetType: String,
        val courseId: Long,
        val ownerId: Long,
        val authorsId: List<Long>,
        val title: String,
        val slug: String,
        val logo: String
)