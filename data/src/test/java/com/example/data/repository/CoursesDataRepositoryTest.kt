package com.example.data.repository

import com.example.data.converter.CourseMainDataConverter
import com.example.data.model.dto.CourseMainDataDto
import com.example.data.model.entity.CourseMainDataEntity
import com.example.data.network.api.Api
import com.example.data.network.api.response.CoursesMainDataResponse
import com.example.data.network.api.response.Meta
import com.example.data.scheduler.AppSchedulerProviderTest
import com.example.data.storage.MainDatabase
import com.example.data.storage.dao.CoursesMainDataDao
import io.reactivex.Flowable
import io.reactivex.Maybe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.junit.Assert.assertEquals
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations


@RunWith(JUnitPlatform::class)
class CoursesDataRepositoryTest : Spek({
    describe("testing repository") {
        describe("mocking resources") {
            MockitoAnnotations.initMocks(this)
            val db: MainDatabase = mock(MainDatabase::class.java)
            val courseDao = mock(CoursesMainDataDao::class.java)
            val api: Api = mock(Api::class.java)


            describe("prepare mock data") {
                val localCourses = mutableListOf(
                        CourseMainDataEntity(1, 1, 111.1, 1, "", 1, 1, listOf(1), "1", "", null),
                        CourseMainDataEntity(2, 2, 100.0, 2, "", 2, 1, listOf(1), "2", "", null),
                        CourseMainDataEntity(3, 3, 90.2, 3, "", 3, 1, listOf(1), "4", "", null),
                        CourseMainDataEntity(4, 4, 78.1, 4, "", 4, 1, listOf(1), "5", "", null)
                )

                val coursesNetwork = arrayListOf<CourseMainDataDto>()
                for (i in 10..20) {
                    coursesNetwork.add(CourseMainDataDto(i.toLong(), i, i.toDouble(), i.toLong(), "$i", i.toLong(), i.toLong(), listOf(i.toLong()), "$i $i", "", null))
                }
                val response = CoursesMainDataResponse(Meta(1, false, false), coursesNetwork)
                val testSchedulers = AppSchedulerProviderTest
                val coursesDataRepository = CoursesDataRepository(api, db, testSchedulers)

                `when`(db.coursesMainDataDao()).thenReturn(courseDao)
                `when`(courseDao.allCourses()).thenReturn(Maybe.just(localCourses))
                `when`(api.searchCourses()).thenReturn(Flowable.just(response))
                doAnswer {
                    val course = it.arguments.first() as CourseMainDataEntity
                    localCourses.remove(course)
                }.`when`(courseDao).delete(ArgumentMatchers.any())

                doAnswer {
                    val course = it.arguments.first() as CourseMainDataEntity
                    localCourses.add(course)
                    course.id
                }.`when`(courseDao).insert(ArgumentMatchers.any())

                on("favorite courses") {

                    it("load favorite courses") {
                        coursesDataRepository.loadCoursesFavorite("")
                                .subscribe(
                                        {
                                            assertEquals(localCourses, it)
                                        },
                                        {
                                            throw it
                                        }
                                ).dispose()
                    }

                    it("search in favorite courses") {
                        coursesDataRepository.loadCoursesFavorite("4")
                                .subscribe(
                                        {
                                            assertEquals(localCourses[2], it.first())
                                        },
                                        {
                                            throw it
                                        }
                                ).dispose()
                    }

                    it("add favorite") {
                        val oldSize = localCourses.size
                        coursesDataRepository.addToFavoriteCourse(CourseMainDataConverter.toModel(coursesNetwork.first()))
                                .subscribe({
                                    assertEquals(oldSize + 1, localCourses.size)
                                    assertEquals(localCourses.last().id, coursesNetwork.first().id)
                                }, {
                                    throw it
                                }).dispose()
                    }

                    it("remove favorite") {
                        val oldSize = localCourses.size
                        coursesDataRepository.removeFromFavorite(CourseMainDataConverter.toModel(localCourses.first()))
                                .subscribe({
                                    assertEquals(oldSize - 1, localCourses.size)
                                }, {
                                    throw it
                                }).dispose()
                    }
                }

                on("courses from server") {
                    it("loading") {
                        coursesDataRepository.loadCoursesFromServer("")
                                .subscribe({
                                    assertEquals(coursesNetwork, it)
                                }, {
                                    throw it
                                }).dispose()
                    }
                }
            }
        }

    }
})