package com.example.data.model.dto

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

internal data class UserDto(
        @SerializedName("id")
        val id: Long,
        @SerializedName("profile")
        val profile: Long,
        @SerializedName("is_private")
        val isPrivate: Boolean,
        @SerializedName("is_active")
        val isActive: Boolean,
        @SerializedName("is_guest")
        val isGuest: Boolean,
        @SerializedName("is_organization")
        val isOrganization: Boolean,
        @SerializedName("short_bio")
        val shortBio: String,
        @SerializedName("details")
        val details: String?,
        @SerializedName("first_name")
        val firstName: String,
        @SerializedName("last_name")
        val lastName: String?,
        @SerializedName("full_name")
        val fullName: String,
        @SerializedName("alias")
        val alias: String?,
        @SerializedName("avatar")
        val avatar: String?,
        @SerializedName("city")
        val city: String?,
        @SerializedName("level")
        val level: Int,
        @SerializedName("level_title")
        val levelTitle: String,
        @SerializedName("tag_progresses")
        val tagProgresses: List<String>,
        @SerializedName("knowledge")
        val knowledge: Int,
        @SerializedName("knowledge_rank")
        val knowledgeRank: Int,
        @SerializedName("reputation")
        val reputation: Int,
        @SerializedName("reputation_rank")
        val reputationRank: Int,
        @SerializedName("join_date")
        val joinDate: DateTime,
        @SerializedName("social_profiles")
        val socialProfiles: List<String>,
        @SerializedName("solved_steps_count")
        val solvedStepsCount: Int,
        @SerializedName("created_courses_count")
        val createdCoursesCount: Int,
        @SerializedName("created_lessons_count")
        val createdLessonsCount: Int,
        @SerializedName("issued_certificates_count")
        val issuedCertificatesCount: Int,
        @SerializedName("followers_count")
        val followersCount: Int
)