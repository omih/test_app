package com.example.data.converter

import com.example.data.model.dto.UserDto
import com.example.data.model.entity.UserEntity

object UserConverter {
    fun toEntity(userDto: UserDto): UserEntity {
        val id = userDto.id
        val profile = userDto.profile
        val isPrivate = userDto.isPrivate
        val isActive = userDto.isActive
        val isGuest = userDto.isGuest
        val isOrganization = userDto.isOrganization
        val shortBio = userDto.shortBio
        val details = userDto.details
        val firstName = userDto.firstName
        val lastName = userDto.lastName
        val fullName = userDto.fullName
        val alias = userDto.alias
        val avatar = userDto.avatar
        val city = userDto.city
        val level = userDto.level
        val levelTitle = userDto.levelTitle
        val tagProgresses = userDto.tagProgresses
        val knowledge = userDto.knowledge
        val knowledgeRank = userDto.knowledgeRank
        val reputation = userDto.reputation
        val reputationRank = userDto.reputationRank
        val joinDate = userDto.joinDate
        val socialProfiles = userDto.socialProfiles
        val solvedStepsCount = userDto.solvedStepsCount
        val createdCoursesCount = userDto.createdCoursesCount
        val createdLessonsCount = userDto.createdLessonsCount
        val issuedCertificatesCount = userDto.issuedCertificatesCount
        val followersCount = userDto.followersCount

        return UserEntity(id, profile, isPrivate, isActive, isGuest, isOrganization, shortBio, details, firstName, lastName, fullName, alias, avatar, city, level, levelTitle, tagProgresses, knowledge, knowledgeRank, reputation, reputationRank, joinDate, socialProfiles, solvedStepsCount, createdCoursesCount, createdLessonsCount, issuedCertificatesCount, followersCount)
    }
}