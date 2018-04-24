package com.example.mikhail.internal.di.component

import com.example.mikhail.App
import com.example.mikhail.internal.di.component.courses.CoursesComponent
import com.example.mikhail.internal.di.module.AppModule
import com.example.mikhail.internal.di.scope.AppScope
import com.example.mikhail.presentation.presenter.SinglePresenter
import com.example.mikhail.ui.activity.SingleActivity
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(app: App)
    fun inject(singlePresenter: SinglePresenter)
    fun inject(singleActivity: SingleActivity)

    fun coursesComponentBuilder(): CoursesComponent.Builder
}