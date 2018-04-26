package com.example.data.converter

import com.example.data.model.dto.CourseFullDataDto
import com.example.data.model.entity.CourseFullDataEntity

internal object CourseFullDataConverter {
    fun toEntity(courseDto: CourseFullDataDto): CourseFullDataEntity {
        val id = courseDto.id
        val summary = courseDto.summary
        val workload = courseDto.workload
        val intro = courseDto.intro
        val courseFormat = courseDto.courseFormat
        val targetAudience = courseDto.targetAudience
        val isCertificateAutoIssued = courseDto.isCertificateAutoIssued
        val certificateRegularThreshold = courseDto.certificateRegularThreshold
        val certificateDistinctionThreshold = courseDto.certificateDistinctionThreshold
        val instructors = courseDto.instructors
        val certificate = courseDto.certificate
        val requirements = courseDto.requirements
        val description = courseDto.description
        val sections = courseDto.sections
        val totalUnits = courseDto.totalUnits
        val enrollment = courseDto.enrollment
        val isFavorite = courseDto.isFavorite
        val progress = courseDto.progress
        val firstLesson = courseDto.firstLesson
        val firstUnit = courseDto.firstUnit
        val certificateLink = courseDto.certificateLink
        val certificateRegularLink = courseDto.certificateRegularLink
        val certificateDistinctionLink = courseDto.certificateDistinctionLink
        val scheduleLink = courseDto.scheduleLink
        val scheduleLongLink = courseDto.scheduleLongLink
        val firstDeadline = courseDto.firstDeadline
        val lastDeadline = courseDto.lastDeadline
        val subscriptions = courseDto.subscriptions
        val announcements = courseDto.announcements
        val isContest = courseDto.isContest
        val isSelfPaced = courseDto.isSelfPaced
        val isAdaptive = courseDto.isAdaptive
        val isIdeaCompatible = courseDto.isIdeaCompatible
        val lastStep = courseDto.lastStep
        val introVideo = courseDto.introVideo
        val socialProviders = courseDto.socialProviders
        val authors = courseDto.authors
        val tags = courseDto.tags
        val hasTutors = courseDto.hasTutors
        val isPromoted = courseDto.isPromoted
        val isEnabled = courseDto.isEnabled
        val isProctored = courseDto.isProctored
        val proctorUrl = courseDto.proctorUrl
        val reviewSummary = courseDto.reviewSummary
        val scheduleType = courseDto.scheduleType
        val certificatesCount = courseDto.certificatesCount
        val learnersCount = courseDto.learnersCount
        val timeToComplete = courseDto.timeToComplete
        val isPopular = courseDto.isPopular
        val similarCourses = courseDto.similarCourses
        val isUnsuitable = courseDto.isUnsuitable
        val owner = courseDto.owner
        val language = courseDto.language
        val isFeatured = courseDto.isFeatured
        val isPublic = courseDto.isPublic
        val title = courseDto.title
        val slug = courseDto.slug
        val beginDate = courseDto.beginDate
        val endDate = courseDto.endDate
        val softDeadline = courseDto.softDeadline
        val hardDeadline = courseDto.hardDeadline
        val gradingPolicy = courseDto.gradingPolicy
        val beginDateSource = courseDto.beginDateSource
        val endDateSource = courseDto.endDateSource
        val softDeadlineSource = courseDto.softDeadlineSource
        val hardDeadlineSource = courseDto.hardDeadlineSource
        val gradingPolicySource = courseDto.gradingPolicySource
        val isActive = courseDto.isActive
        val createDate = courseDto.createDate
        val updateDate = courseDto.updateDate
        val learnersGroup = courseDto.learnersGroup
        val testersGroup = courseDto.testersGroup
        val moderatorsGroup = courseDto.moderatorsGroup
        val teachersGroup = courseDto.teachersGroup
        val adminsGroup = courseDto.adminsGroup
        val discussionsCount = courseDto.discussionsCount
        val discussionProxy = courseDto.discussionProxy
        val discussionThreads = courseDto.discussionThreads
        val ltiConsumerKey = courseDto.ltiConsumerKey
        val ltiSecretKey = courseDto.ltiSecretKey
        
        return CourseFullDataEntity(id, summary, workload, intro, courseFormat, targetAudience, isCertificateAutoIssued, certificateRegularThreshold, certificateDistinctionThreshold, instructors, certificate, requirements, description, sections, totalUnits, enrollment, isFavorite, progress, firstLesson, firstUnit, certificateLink, certificateRegularLink, certificateDistinctionLink, scheduleLink, scheduleLongLink, firstDeadline, lastDeadline, subscriptions, announcements, isContest, isSelfPaced, isAdaptive, isIdeaCompatible, lastStep, introVideo, socialProviders, authors, tags, hasTutors, isPromoted, isEnabled, isProctored, proctorUrl, reviewSummary, scheduleType, certificatesCount, learnersCount, timeToComplete, isPopular, similarCourses, isUnsuitable, owner, language, isFeatured, isPublic, title, slug, beginDate, endDate, softDeadline, hardDeadline, gradingPolicy, beginDateSource, endDateSource, softDeadlineSource, hardDeadlineSource, gradingPolicySource, isActive, createDate, updateDate, learnersGroup, testersGroup, moderatorsGroup, teachersGroup, adminsGroup, discussionsCount, discussionProxy, discussionThreads, ltiConsumerKey, ltiSecretKey)
    }

//    fun toModel(entity: CourseFullDataEntity): CourseFullData {
//
//    }
}