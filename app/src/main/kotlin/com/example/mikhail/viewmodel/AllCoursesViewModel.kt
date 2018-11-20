package com.example.mikhail.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.model.model.CourseMainData
import com.example.model.usecase.CoursesUseCase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllCoursesViewModel
@Inject constructor(private val coursesUseCase: CoursesUseCase) : BaseViewModel() {
    var courses: MutableLiveData<List<Item<ViewHolder>>> = MutableLiveData()

    private var searchString = ""

    private val catchLoadCourses = CoroutineExceptionHandler { _, _ ->
        createView(listOf())
    }

    fun loadCourses(search: String = "") {
        searchString = search
        GlobalScope.launch(Dispatchers.Main + catchLoadCourses) {
            val result = coursesUseCase.loadCoursesFromServer(searchString)
            createView(result.await())
        }
    }

    private fun createView(list: List<CourseMainData>) {
        courses.value = list.map { CourseItemAdapter(it) }
    }

    fun addOrRemoveFromFavorite(course: CourseMainData) {
        GlobalScope.launch {
            if (course.favorite) {
                coursesUseCase.removeFromFavorite(course)
            } else {
                coursesUseCase.addToFavorite(course)
            }.await()
        }.invokeOnCompletion {
            loadCourses(searchString)
        }
    }

    fun refreshCourses() {
        loadCourses(searchString)
    }
}