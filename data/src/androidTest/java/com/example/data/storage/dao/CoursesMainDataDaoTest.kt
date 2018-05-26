package com.example.data.storage.dao

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.data.model.entity.CourseMainDataEntity
import com.example.data.storage.MainDatabase
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CoursesMainDataDaoTest {
    companion object {
        val context = InstrumentationRegistry.getTargetContext()
        val query = "%%"

        internal val course1 = CourseMainDataEntity(
            1, 1, 1.11, 2, "test",
            33, 44, listOf(99, 101), "test course", "super course", null, true
        )
        internal val course2 = CourseMainDataEntity(
            2, 1000, 9.99, 9999, "test new",
            301013, 123144, listOf(99, 101, 12), "test course new", "super puper course", null, true
        )

        val db = Room.inMemoryDatabaseBuilder(context, MainDatabase::class.java).build()
    }

    @Before
    fun prepareDb() {
        db.clearDatabase()
        db.coursesMainDataDao().insert(course1)
    }

    @Test
    fun testGet() {
        val courses = db.coursesMainDataDao().allCourses(query)
        assertEquals(course1, courses[0])
    }

    @Test
    fun testReplace() {
        val newTitle = "title course update"
        val courseUpdate = course1.copy(title = newTitle)
        db.coursesMainDataDao().insert(courseUpdate)
        val courses = db.coursesMainDataDao().allCourses(query)

        assertEquals(newTitle, courses[0].title)
    }

    @Test
    fun testDelete() {
        var courses = db.coursesMainDataDao().allCourses(query)
        assertArrayEquals(arrayOf(course1), courses.toTypedArray())

        db.coursesMainDataDao().delete(course1)
        courses = db.coursesMainDataDao().allCourses(query)

        assertArrayEquals(arrayOf<CourseMainDataEntity>(), courses.toTypedArray())
    }

    @Test
    fun testAdd() {
        var courses = db.coursesMainDataDao().allCourses(query)

        assertArrayEquals(arrayOf(course1), courses.toTypedArray())

        db.coursesMainDataDao().insert(course2)
        courses = db.coursesMainDataDao().allCourses(query)

        assertArrayEquals(arrayOf(course1, course2), courses.toTypedArray())
    }
}