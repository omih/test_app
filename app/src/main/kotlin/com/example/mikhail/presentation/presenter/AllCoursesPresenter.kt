package com.example.mikhail.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.mikhail.internal.di.DI
import com.example.mikhail.presentation.BasePresenter
import com.example.mikhail.presentation.view.AllCoursesView
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.model.model.CourseMainData
import com.example.model.usecase.CoursesUseCase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class AllCoursesPresenter: BasePresenter<AllCoursesView>() {
    init {
        DI.componentManager.courseComponent.inject(this)

        loadCourses()
    }

    @Inject
    internal lateinit var router: Router

    @Inject
    internal lateinit var coursesUseCase: CoursesUseCase

    fun loadCourses() {
        coursesUseCase.getCoursesMainData()
                .subscribe({
                    viewState.showAllCourses(createView(it))
                }, {
                    Log.e("err", it.message)
                })
    }

    private fun createView(list: List<CourseMainData>): List<Item<ViewHolder>> {
        return list.map { CourseItemAdapter(it) }
    }

}