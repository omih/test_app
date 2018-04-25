package com.example.mikhail.presentation.presenter

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.example.mikhail.internal.di.DI
import com.example.mikhail.presentation.BasePresenter
import com.example.mikhail.presentation.view.CoursesView
import com.example.model.usecase.CoursesUseCase
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class CoursesPresenter : BasePresenter<CoursesView>() {

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
                    viewState.show(it)
                }, {
                    Log.e("err", it.message)
                })
    }
}