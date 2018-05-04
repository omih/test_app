package com.example.mikhail.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.mikhail.internal.di.DI
import com.example.mikhail.presentation.BasePresenter
import com.example.mikhail.presentation.view.AllCoursesView
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.model.model.CourseMainData
import com.example.model.usecase.CoursesUseCase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

@InjectViewState
class AllCoursesPresenter : BasePresenter<AllCoursesView>() {
    init {
        DI.componentManager.courseComponent.inject(this)

        loadCourses()
    }

    @Inject
    protected lateinit var coursesUseCase: CoursesUseCase

    private var searchString = ""

    fun loadCourses(search: String = "") {
        searchString = search
        viewState.progressShow()
        safeSingleSubscribe {
            coursesUseCase.loadCoursesFromServer(searchString)
                    .subscribe({
                        if (it.isEmpty()) {
                            viewState.showCoursesAbsent()
                        } else {
                            viewState.showAllCourses(createView(it))
                            viewState.contentShow()
                        }
                    }, {
                        viewState.showCoursesAbsent()
                    })
        }
    }

    private fun createView(list: List<CourseMainData>): List<Item<ViewHolder>> {
        return list.map { CourseItemAdapter(it) }
    }

    fun addOrRemoveFromFavorite(course: CourseMainData) {
        safeSubscribe {
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