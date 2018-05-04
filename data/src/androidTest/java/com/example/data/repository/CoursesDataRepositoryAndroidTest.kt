package com.example.data.repository

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.data.converter.CourseMainDataConverter
import com.example.data.model.dto.CourseMainDataDto
import com.example.data.network.api.Api
import com.example.data.network.api.response.CoursesMainDataResponse
import com.example.data.network.api.response.Meta
import com.example.data.scheduler.AppSchedulerProvider
import com.example.data.storage.MainDatabase
import com.example.model.model.CourseMainData
import io.reactivex.Flowable
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenMock

@RunWith(AndroidJUnit4::class)
class CoursesDataRepositoryAndroidTest {

    val db: MainDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getContext(), MainDatabase::class.java).build()
    val api: Api = mock(Api::class.java)
    val coursesDataRepository = CoursesDataRepository(api, db, AppSchedulerProvider)

    internal val preparedFavoriteCourses = arrayListOf(
            CourseMainData(1, 1, 111.1, 1, "", 1, 1, listOf(1), "1", "", null),
            CourseMainData(2, 2, 100.0, 2, "", 2, 1, listOf(1), "2", "", null),
            CourseMainData(3, 3, 90.2, 3, "", 3, 1, listOf(1), "4", "", null),
            CourseMainData(4, 4, 78.1, 4, "", 4, 1, listOf(1), "5", "", null)
    )

    internal val coursesNetwork = arrayListOf<CourseMainDataDto>()

    init {
        MockitoAnnotations.initMocks(this)

        for (i in 10..20) {
            coursesNetwork.add(CourseMainDataDto(i.toLong(), i, i.toDouble(), i.toLong(), "$i", i.toLong(), i.toLong(), listOf(i.toLong()), "$i $i", "", null))
        }
        val response = CoursesMainDataResponse(Meta(1, false, false), coursesNetwork)

        whenMock(api.searchCourses()).thenReturn(Flowable.just(response))
    }

    @Before
    fun initFavoriteCourses() {
        db.clearDatabase()
        db.coursesMainDataDao().insert(*preparedFavoriteCourses.map { CourseMainDataConverter.toEntity(it) }.toTypedArray())
    }

    @Test
    fun testLoadFavoriteCourses() {
        coursesDataRepository.loadCoursesFavorite("")
                .subscribe(
                        {
                            assertEquals(preparedFavoriteCourses, it)
                        },
                        {
                            throw it
                        }
                ).dispose()
    }

    @Test
    fun testSearchFavoriteCourses() {
        coursesDataRepository.loadCoursesFavorite("4")
                .subscribe(
                        {
                            assertEquals(preparedFavoriteCourses[2], it.first())
                        },
                        {
                            throw it
                        }
                ).dispose()
    }

    @Test
    fun testLoadCoursesNetwork() {
        coursesDataRepository.loadCoursesFromServer("")
                .subscribe(
                        {
                            val courses = coursesNetwork.map { CourseMainDataConverter.toModel(it) }
                            assertEquals(courses.size, it.size)
                            assertArrayEquals(courses.toTypedArray(), it.toTypedArray())
                        },
                        {
                            throw it
                        }
                ).dispose()
    }

    @Test
    fun testAddFavoriteCourse() {
        coursesDataRepository.addToFavoriteCourse(CourseMainDataConverter.toModel(coursesNetwork.first()))
                .subscribe()

        coursesDataRepository.loadCoursesFavorite("")
                .subscribe {
                    assertNotEquals(preparedFavoriteCourses.size, it.size)
                }
                .dispose()

        coursesDataRepository.loadCoursesFavorite(coursesNetwork.first().title)
                .subscribe({
                    assertEquals(coursesNetwork.first().courseId, it.first().courseId)
                })
    }

    @Test
    fun testRemoveFavoriteCourse() {
        coursesDataRepository.removeFromFavorite(preparedFavoriteCourses.first())
                .subscribe()

        coursesDataRepository.loadCoursesFavorite("")
                .subscribe({
                    assertNotEquals(it.size, preparedFavoriteCourses.size)
                }, {
                    throw it
                }).dispose()
    }

}