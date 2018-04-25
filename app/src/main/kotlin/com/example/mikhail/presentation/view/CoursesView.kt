package com.example.mikhail.presentation.view

import com.arellomobile.mvp.MvpView
import com.example.model.model.CourseMainData

interface CoursesView : MvpView {
    fun show(courses: MutableList<CourseMainData>)
}