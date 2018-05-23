package com.example.mikhail.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.model.model.CourseMainData
import com.example.model.usecase.CoursesUseCase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class AllCoursesViewModel
@Inject constructor(private val coursesUseCase: CoursesUseCase) : BaseViewModel() {
    var courses: MutableLiveData<List<Item<ViewHolder>>> = MutableLiveData()

    private var searchString = ""

    fun loadCourses(search: String = "") {
        searchString = search
        safeSingleSubscribe {
            coursesUseCase.loadCoursesFromServer(searchString)
                .subscribe({
                    createView(it)
                }, {
                    createView(listOf())
                })
        }
    }

    private fun createView(list: List<CourseMainData>) {
        courses.value = list.map { CourseItemAdapter(it) }
    }

    fun addOrRemoveFromFavorite(course: CourseMainData) {
        safeSingleSubscribe {
            if (course.favorite) {
                coursesUseCase.removeFromFavorite(course)
                    .subscribe({
                        loadCourses(searchString)
                    }, {})
            } else {
                coursesUseCase.addToFavorite(course)
                    .subscribe({
                        loadCourses(searchString)
                    }, {})
            }
        }
    }
}