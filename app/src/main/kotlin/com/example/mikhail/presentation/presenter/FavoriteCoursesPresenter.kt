package com.example.mikhail.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.mikhail.internal.di.DI
import com.example.mikhail.presentation.BasePresenter
import com.example.mikhail.presentation.view.FavoriteCoursesView
import com.example.mikhail.ui.adapter.CourseItemAdapter
import com.example.model.model.CourseMainData
import com.example.model.usecase.CoursesUseCase
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class FavoriteCoursesPresenter: BasePresenter<FavoriteCoursesView>() {
    init {
        DI.componentManager.courseComponent.inject(this)

        loadFavoriteCourses()
    }

    @Inject
    protected lateinit var router: Router

    @Inject
    protected lateinit var coursesUseCase: CoursesUseCase

    private var searchString = ""

    fun loadFavoriteCourses(search: String = "") {
        searchString = search
        viewState.progressShow()
        safeSubscribe {
            coursesUseCase.loadFavoriteCourses(searchString)
                    .subscribe({
                        if (it.isEmpty()) {
                            viewState.showCoursesAbsent()
                        } else {
                            viewState.contentShow()
                            viewState.showFavoriteCourses(createView(it))
                        }
                    }, {
                        viewState.showCoursesAbsent()
                    })
        }
    }

    private fun createView(list: List<CourseMainData>): List<Item<ViewHolder>> {
        return list.map { CourseItemAdapter(it) }
    }

    fun removeFromFavorite(course: CourseMainData) {
        safeSubscribe {
            coursesUseCase.removeFromFavorite(course)
                    .subscribe(
                            { loadFavoriteCourses(searchString) },
                            { viewState.showCoursesAbsent() } )
        }
    }

}