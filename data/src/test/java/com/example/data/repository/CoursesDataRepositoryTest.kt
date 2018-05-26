package com.example.data.repository

import com.example.data.converter.CourseMainDataConverter
import com.example.data.model.dto.CourseMainDataDto
import com.example.data.model.entity.CourseMainDataEntity
import com.example.data.network.api.Api
import com.example.data.network.api.response.CoursesMainDataResponse
import com.example.data.network.api.response.Meta
import com.example.data.storage.MainDatabase
import com.example.data.storage.dao.CoursesMainDataDao
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
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
                val localCoursesEntity = mutableListOf(
                    CourseMainDataEntity(1, 1, 111.1, 1, "", 1, 1, listOf(1), "1", "", null),
                    CourseMainDataEntity(2, 2, 100.0, 2, "", 2, 1, listOf(1), "2", "", null),
                    CourseMainDataEntity(3, 3, 90.2, 3, "", 3, 1, listOf(1), "4", "", null),
                    CourseMainDataEntity(4, 4, 78.1, 4, "", 4, 1, listOf(1), "5", "", null)
                )

                val coursesNetwork = arrayListOf<CourseMainDataDto>()
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
                val response =
                    async { CoursesMainDataResponse(Meta(1, false, false), coursesNetwork) }
                val coursesDataRepository = CoursesDataRepository(api, db)

                `when`(db.coursesMainDataDao()).thenReturn(courseDao)
                `when`(api.searchCourses()).thenReturn(response)
                `when`(courseDao.allCourses("")).thenReturn(localCoursesEntity)
                doAnswer {
                    val query = (it.arguments.first() as String).replace("%", "")
                    if (query.isEmpty())
                        localCoursesEntity
                    else
                        localCoursesEntity.filter { it.title.contains(query) }
                }.`when`(courseDao).allCourses(ArgumentMatchers.anyString())
                doAnswer {
                    val courseId = it.arguments.first() as Long
                    localCoursesEntity.removeIf { it.courseId == courseId }
                }.`when`(courseDao).deleteCourse(ArgumentMatchers.anyLong())

                doAnswer {
                    val course = it.arguments.first() as CourseMainDataEntity
                    localCoursesEntity.add(course)
                    course.id
                }.`when`(courseDao).insert(ArgumentMatchers.any())

                on("favorite courses") {

                    it("load favorite courses") {
                        val courses = coursesDataRepository.coursesFromLocal("")
                        val coursesLocal =
                            localCoursesEntity.map { CourseMainDataConverter.toModel(it) }
                        assertEquals(coursesLocal, courses)
                    }

                    it("search in favorite courses") {
                        val courses = coursesDataRepository.coursesFromLocal("4")
                        assertEquals(
                            CourseMainDataConverter.toModel(localCoursesEntity[2]),
                            courses.first()
                        )
                    }

                    it("add favorite") {
                        val oldSize = localCoursesEntity.size
                        coursesDataRepository.addToFavoriteCourse(
                            CourseMainDataConverter.toModel(
                                coursesNetwork.first()
                            )
                        )
                        assertEquals(oldSize + 1, localCoursesEntity.size)
                        assertEquals(localCoursesEntity.last().id, coursesNetwork.first().id)
                    }

                    it("remove favorite") {
                        val oldSize = localCoursesEntity.size
                        coursesDataRepository.removeFavoriteCourse(localCoursesEntity.first().courseId)
                        assertEquals(oldSize - 1, localCoursesEntity.size)
                    }
                }

                on("courses from server") {
                    it("loading") {
                        launch {
                            val courses = coursesDataRepository.loadCoursesFromServer("")
                            assertEquals(coursesNetwork, courses)
                        }
                    }
                }
            }
        }

    }
})