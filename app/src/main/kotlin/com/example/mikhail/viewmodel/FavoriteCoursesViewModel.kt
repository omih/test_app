package com.example.mikhail.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.model.model.CourseMainData
import com.example.model.usecase.CoursesUseCase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

class FavoriteCoursesViewModel
@Inject constructor(private val coursesUseCase: CoursesUseCase) : BaseViewModel() {

    var courses: MutableLiveData<List<Item<ViewHolder>>> = MutableLiveData()
    private var searchString = ""

    fun loadFavoriteCourses(search: String = "") {
        searchString = search
        safeSingleSubscribe {
            coursesUseCase.loadFavoriteCourses(searchString)
                .subscribe({
                    if (it.isEmpty()) {
                        createView(listOf())
                    } else {
                        createView(it)
                    }
                }, {
                    createView(listOf())
                })
        }
    }

    private fun createView(list: List<CourseMainData>) {
        courses.value = list.map { CourseItemAdapter(it) }
    }

    fun removeFromFavorite(course: CourseMainData) {
        safeSingleSubscribe {
            coursesUseCase.removeFromFavorite(course)
                .subscribe(
                    { loadFavoriteCourses(searchString) },
                    { createView(listOf()) }
                )
        }
    }
}