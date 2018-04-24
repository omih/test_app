package com.example.model.model

import org.joda.time.DateTime

data class User(
        var id: Long,

        var profile: Long,

        var isPrivate: Boolean,

        var isActive: Boolean,

        var isGuest: Boolean,

        var isOrganization: Boolean,

        var shortBio: String,

        var details: String?,

        var firstName: String,

        var lastName: String?,

        var fullName: String,

        var alias: String?,

        var avatar: String?,

        var city: String?,

        var level: Int,

        var levelTitle: String,

        var tagProgresses: List<String>,

        var knowledge: Int,

        var knowledgeRank: Int,

        var reputation: Int,

        var reputationRank: Int,

        var joinDate: DateTime,

        var socialProfiles: List<String>,

        var solvedStepsCount: Int,

        var createdCoursesCount: Int,

        var createdLessonsCount: Int,

        var issuedCertificatesCount: Int,

        var followersCount: Int
)