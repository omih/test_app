package com.example.mikhail.core.di.component

import com.example.mikhail.App
import com.example.mikhail.core.di.component.courses.CoursesComponent
import com.example.mikhail.core.di.module.AppModule
import com.example.mikhail.core.di.scope.AppScope
import com.example.mikhail.core.di.viewmodel.ViewModelModule
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, ViewModelModule::class])
interface AppComponent {
    fun inject(app: App)

    fun coursesComponentBuilder(): CoursesComponent.Builder
}