package com.example.mikhail.presentation.view

import com.arellomobile.mvp.MvpView
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder

interface FavoriteCoursesView: MvpView {
    fun showFavoriteCourses(courses: List<Item<ViewHolder>>)
    fun progressShow()
    fun contentShow()
    fun showCoursesAbsent()
}