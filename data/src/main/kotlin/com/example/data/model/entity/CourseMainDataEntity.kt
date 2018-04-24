package com.example.data.model.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.data.storage.Tables

@Entity(tableName = Tables.COURSE_MAIN_DATA)
data class CourseMainDataEntity(
        @PrimaryKey
        var id: Long = 0,

        @ColumnInfo(name = "position")
        var position: Int = 0,

        @ColumnInfo(name = "score")
        var score: Double = 0.0,

        @ColumnInfo(name = "target_id")
        var targetId: Long = 0,

        @ColumnInfo(name = "target_type")
        var targetType: String = "",

        @ColumnInfo(name = "course_id")
        var courseId: Long = 0,

        @ColumnInfo(name = "owner_id")
        var ownerId: Long = 0,

        @ColumnInfo(name = "authors_id")
        var authorsId: List<Long> = listOf(),

        @ColumnInfo(name = "title")
        var title: String = "",

        @ColumnInfo(name = "slug")
        var slug: String = "",

        @ColumnInfo(name = "logo")
        var logo: String = ""
)