package com.example.data.model.dto

import com.google.gson.annotations.SerializedName
import org.joda.time.DateTime

internal data class CourseFullDataDto(
        @SerializedName("id")
        val id: Long,
        @SerializedName("summary")
        val summary: String,
        @SerializedName("workload")
        val workload: String,
        @SerializedName("intro")
        val intro: String,
        @SerializedName("course_format")
        val courseFormat: String,
        @SerializedName("target_audience")
        val targetAudience: String,
        @SerializedName("is_certificate_auto_issued")
        val isCertificateAutoIssued: Boolean,
        @SerializedName("certificate_regular_threshold")
        val certificateRegularThreshold: Int,
        @SerializedName("certificate_distinction_threshold")
        val certificateDistinctionThreshold: Int,
        @SerializedName("instructors")
        val instructors: List<Long>,
        @SerializedName("certificate")
        val certificate: String,
        @SerializedName("requirements")
        val requirements: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("sections")
        val sections: List<String>,
        @SerializedName("total_units")
        val totalUnits: Int,
        @SerializedName("enrollment")
        val enrollment: String?,
        @SerializedName("is_favorite")
        val isFavorite: Boolean,
        @SerializedName("progress")
        val progress: Int?,
        @SerializedName("first_lesson")
        val firstLesson: String?,
        @SerializedName("first_unit")
        val firstUnit: String?,
        @SerializedName("certificate_link")
        val certificateLink: String?,
        @SerializedName("certificate_regular_link")
        val certificateRegularLink: String?,
        @SerializedName("certificate_distinction_link")
        val certificateDistinctionLink: String?,
        @SerializedName("schedule_link")
        val scheduleLink: String?,
        @SerializedName("schedule_long_link")
        val scheduleLongLink: String?,
        @SerializedName("first_deadline")
        val firstDeadline: String?,
        @SerializedName("last_deadline")
        val lastDeadline: String?,
        @SerializedName("subscriptions")
        val subscriptions: List<String>?,
        @SerializedName("announcements")
        val announcements: List<String>?,
        @SerializedName("is_contest")
        val isContest: Boolean,
        @SerializedName("is_self_paced")
        val isSelfPaced: Boolean,
        @SerializedName("is_adaptive")
        val isAdaptive: Boolean,
        @SerializedName("is_idea_compatible")
        val isIdeaCompatible: Boolean,
        @SerializedName("last_step")
        val lastStep: String?,
        @SerializedName("intro_video")
        val introVideo: List<String>?,
        @SerializedName("social_providers")
        val socialProviders: List<String>,
        @SerializedName("authors")
        val authors: List<Long>,
        @SerializedName("tags")
        val tags: List<String>,
        @SerializedName("has_tutors")
        val hasTutors: Boolean,
        @SerializedName("is_promoted")
        val isPromoted: Boolean,
        @SerializedName("is_enabled")
        val isEnabled: Boolean,
        @SerializedName("is_proctored")
        val isProctored: Boolean,
        @SerializedName("proctor_url")
        val proctorUrl: String?,
        @SerializedName("review_summary")
        val reviewSummary: Int?,
        @SerializedName("schedule_type")
        val scheduleType: String?,
        @SerializedName("certificates_count")
        val certificatesCount: Int,
        @SerializedName("learners_count")
        val learnersCount: Int,
        @SerializedName("time_to_complete")
        val timeToComplete: Long,
        @SerializedName("is_popular")
        val isPopular: Boolean,
        @SerializedName("similar_courses")
        val similarCourses: List<Long>,
        @SerializedName("is_unsuitable")
        val isUnsuitable: Boolean,
        @SerializedName("owner")
        val owner: Long,
        @SerializedName("language")
        val language: String,
        @SerializedName("is_featured")
        val isFeatured: Boolean,
        @SerializedName("is_public")
        val isPublic: Boolean,
        @SerializedName("title")
        val title: String,
        @SerializedName("slug")
        val slug: String,
        @SerializedName("begin_date")
        val beginDate: DateTime?,
        @SerializedName("end_date")
        val endDate: DateTime?,
        @SerializedName("soft_deadline")
        val softDeadline: DateTime?,
        @SerializedName("hard_deadline")
        val hardDeadline: DateTime?,
        @SerializedName("grading_policy")
        val gradingPolicy: String,
        @SerializedName("begin_date_source")
        val beginDateSource: DateTime?,
        @SerializedName("end_date_source")
        val endDateSource: DateTime,
        @SerializedName("soft_deadline_source")
        val softDeadlineSource: DateTime?,
        @SerializedName("hard_deadline_source")
        val hardDeadlineSource: DateTime?,
        @SerializedName("grading_policy_source")
        val gradingPolicySource: String,
        @SerializedName("is_active")
        val isActive: Boolean,
        @SerializedName("create_date")
        val createDate: DateTime,
        @SerializedName("update_date")
        val updateDate: DateTime?,
        @SerializedName("learners_group")
        val learnersGroup: String?,
        @SerializedName("testers_group")
        val testersGroup: String?,
        @SerializedName("moderators_group")
        val moderatorsGroup: String?,
        @SerializedName("teachers_group")
        val teachersGroup: String?,
        @SerializedName("admins_group")
        val adminsGroup: String?,
        @SerializedName("discussions_count")
        val discussionsCount: Int,
        @SerializedName("discussion_proxy")
        val discussionProxy: String?,
        @SerializedName("discussion_threads")
        val discussionThreads: String?,
        @SerializedName("lti_consumer_key")
        val ltiConsumerKey: String,
        @SerializedName("lti_secret_key")
        val ltiSecretKey: String
)