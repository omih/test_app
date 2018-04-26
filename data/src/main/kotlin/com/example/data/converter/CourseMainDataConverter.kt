package com.example.data.converter

import com.example.data.model.dto.CourseMainDataDto
import com.example.data.model.entity.CourseMainDataEntity
import com.example.model.model.CourseMainData

internal object CourseMainDataConverter {
    fun toEntity(courseDto: CourseMainDataDto): CourseMainDataEntity {
        val id: Long = courseDto.id
        val position: Int = courseDto.position
        val score: Double = courseDto.score
        val targetId: Long = courseDto.targetId 
        val targetType: String = courseDto.targetType
        val courseId: Long = courseDto.courseId
        val ownerId: Long = courseDto.ownerId
        val authorsId: List<Long> = courseDto.authorsId
        val title: String = courseDto.title
        val slug: String = courseDto.slug
        val logo: String? = courseDto.logo
        
        return CourseMainDataEntity(id, position, score, targetId, targetType, courseId, ownerId, authorsId, title, slug, logo)
    }

    fun toModel(dto: CourseMainDataDto): CourseMainData {
        val id = dto.id
        val position = dto.position
        val score = dto.score
        val targetId = dto.targetId
        val targetType = dto.targetType
        val courseId = dto.courseId
        val ownerId = dto.ownerId
        val authorsId = dto.authorsId
        val title = dto.title
        val slug = dto.slug
        val logo = dto.logo

        return CourseMainData(id, position, score, targetId, targetType, courseId, ownerId, authorsId, title, slug, logo)
    }

    fun toModel(entity: CourseMainDataEntity): CourseMainData {
        val id = entity.id
        val position = entity.position
        val score = entity.score
        val targetId = entity.targetId
        val targetType = entity.targetType
        val courseId = entity.courseId
        val ownerId = entity.ownerId
        val authorsId = entity.authorsId
        val title = entity.title
        val slug = entity.slug
        val logo = entity.logo

        return CourseMainData(id, position, score, targetId, targetType, courseId, ownerId, authorsId, title, slug, logo)
    }
}