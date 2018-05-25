package com.example.mikhail.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.model.model.CourseMainData
import com.example.model.usecase.CoursesUseCase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.experimental.CoroutineExceptionHandler
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class FavoriteCoursesViewModel
@Inject constructor(private val coursesUseCase: CoursesUseCase) : BaseViewModel() {

    var courses: MutableLiveData<List<Item<ViewHolder>>> = MutableLiveData()
    private var searchString = ""

    private val catchLoadCourses = CoroutineExceptionHandler { _, _ ->
        createView(listOf())
    }

    fun loadFavoriteCourses(search: String = "") {
        searchString = search
        launch(UI + catchLoadCourses) {
            val result = coursesUseCase.loadFavoriteCourses(searchString)
            createView(result.await())
        }
    }

    private fun createView(list: List<CourseMainData>) {
        courses.value = list.map { CourseItemAdapter(it) }
    }

    fun removeFromFavorite(course: CourseMainData) {
        launch(UI + catchLoadCourses) {
            coursesUseCase.removeFromFavorite(course).await()
        }.invokeOnCompletion {
            loadFavoriteCourses(searchString)
        }
    }

    fun refreshCourses() {
        loadFavoriteCourses(searchString)
    }
}