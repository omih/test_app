package com.example.data.repository

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.data.converter.CourseMainDataConverter
import com.example.data.model.dto.CourseMainDataDto
import com.example.data.network.api.Api
import com.example.data.network.api.response.CoursesMainDataResponse
import com.example.data.network.api.response.Meta
import com.example.data.storage.MainDatabase
import com.example.model.model.CourseMainData
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenMock

@RunWith(AndroidJUnit4::class)
class CoursesDataRepositoryAndroidTest {

    val db: MainDatabase =
        Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MainDatabase::class.java)
            .build()
    val api: Api = mock(Api::class.java)
    val coursesDataRepository = CoursesDataRepository(api, db)

    internal val preparedFavoriteCourses = arrayListOf(
        CourseMainData(1, 1, 111.1, 1, "", 1, 1, listOf(1), "1", "", null, true),
        CourseMainData(2, 2, 100.0, 2, "", 2, 1, listOf(1), "2", "", null, true),
        CourseMainData(3, 3, 90.2, 3, "", 3, 1, listOf(1), "4", "", null, true),
        CourseMainData(4, 4, 78.1, 4, "", 4, 1, listOf(1), "5", "", null, true)
    )

    internal val coursesNetwork = arrayListOf<CourseMainDataDto>()

    init {
        MockitoAnnotations.initMocks(this)

        for (i in 10..20) {
            coursesNetwork.add(
                CourseMainDataDto(
                    i.toLong(),
                    i,
                    i.toDouble(),
                    i.toLong(),
                    "$i",
                    i.toLong(),
                    i.toLong(),
                    listOf(i.toLong()),
                    "$i $i",
                    "",
                    null
                )
            )
        }
        val response = async { CoursesMainDataResponse(Meta(1, false, false), coursesNetwork) }

        whenMock(api.searchCourses()).thenReturn(response)
    }

    @Before
    fun initFavoriteCourses() {
        db.clearDatabase()
        db.coursesMainDataDao()
            .insert(*preparedFavoriteCourses.map { CourseMainDataConverter.toEntity(it) }.toTypedArray())
    }

    @Test
    fun testLoadFavoriteCourses() {
        val courses = coursesDataRepository.coursesFromLocal("")
        assertEquals(preparedFavoriteCourses, courses)
    }

    @Test
    fun testSearchFavoriteCourses() {
        val courses = coursesDataRepository.coursesFromLocal("4")
        assertEquals(preparedFavoriteCourses[2], courses.first())
    }

    @Test
    fun testLoadCoursesNetwork() {
        launch {
            val coursesDb = coursesDataRepository.loadCoursesFromServer("")
            val courses = coursesNetwork.map { CourseMainDataConverter.toModel(it) }
            assertEquals(courses.size, coursesDb.size)
            assertArrayEquals(courses.toTypedArray(), coursesDb.toTypedArray())
        }
    }

    @Test
    fun testAddFavoriteCourse() {
        coursesDataRepository.addToFavoriteCourse(CourseMainDataConverter.toModel(coursesNetwork.first()))

        var courses = coursesDataRepository.coursesFromLocal("")
        assertNotEquals(preparedFavoriteCourses.size, courses.size)

        courses = coursesDataRepository.coursesFromLocal(coursesNetwork.first().title)
        assertEquals(coursesNetwork.first().courseId, courses.first().courseId)
    }

    @Test
    fun testRemoveFavoriteCourse() {
        coursesDataRepository.removeFavoriteCourse(preparedFavoriteCourses.first().courseId)

        val courses = coursesDataRepository.coursesFromLocal("")
        assertNotEquals(courses.size, preparedFavoriteCourses.size)
    }

}