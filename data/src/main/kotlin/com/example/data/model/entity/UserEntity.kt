package com.example.data.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.data.storage.Tables
import org.joda.time.DateTime

@Entity(tableName = Tables.USERS)
internal data class UserEntity(
        @PrimaryKey
        var id: Long = 0,
        @ColumnInfo(name = "profile")
        var profile: Long = 0,
        @ColumnInfo(name = "is_private")
        var isPrivate: Boolean = false,
        @ColumnInfo(name = "is_active")
        var isActive: Boolean = false,
        @ColumnInfo(name = "is_guest")
        var isGuest: Boolean = false,
        @ColumnInfo(name = "is_organization")
        var isOrganization: Boolean = false,
        @ColumnInfo(name = "short_bio")
        var shortBio: String = "",
        @ColumnInfo(name = "details")
        var details: String? = null,
        @ColumnInfo(name = "first_name")
        var firstName: String = "",
        @ColumnInfo(name = "last_name")
        var lastName: String? = null,
        @ColumnInfo(name = "full_name")
        var fullName: String = "",
        @ColumnInfo(name = "alias")
        var alias: String? = null,
        @ColumnInfo(name = "avatar")
        var avatar: String? = null,
        @ColumnInfo(name = "city")
        var city: String? = null,
        @ColumnInfo(name = "level")
        var level: Int = 0,
        @ColumnInfo(name = "level_title")
        var levelTitle: String = "",
        @ColumnInfo(name = "tag_progresses")
        var tagProgresses: List<String> = listOf(),
        @ColumnInfo(name = "knowledge")
        var knowledge: Int = 0,
        @ColumnInfo(name = "knowledge_rank")
        var knowledgeRank: Int = 0,
        @ColumnInfo(name = "reputation")
        var reputation: Int = 0,
        @ColumnInfo(name = "reputation_rank")
        var reputationRank: Int = 0,
        @ColumnInfo(name = "join_date")
        var joinDate: DateTime? = null,
        @ColumnInfo(name = "social_profiles")
        var socialProfiles: List<String> = listOf(),
        @ColumnInfo(name = "solved_steps_count")
        var solvedStepsCount: Int = 0,
        @ColumnInfo(name = "created_courses_count")
        var createdCoursesCount: Int = 0,
        @ColumnInfo(name = "created_lessons_count")
        var createdLessonsCount: Int = 0,
        @ColumnInfo(name = "issued_certificates_count")
        var issuedCertificatesCount: Int = 0,
        @ColumnInfo(name = "followers_count")
        var followersCount: Int = 0
)