package com.example.mikhail.internal.di

import android.content.Context
import com.example.mikhail.internal.di.component.AppComponent
import com.example.mikhail.internal.di.component.DaggerAppComponent
import com.example.mikhail.internal.di.component.courses.CoursesComponent
import com.example.mikhail.internal.di.module.AppModule
import com.example.mikhail.internal.di.module.courses.CoursesModule

class ComponentManager(private val context: Context) {
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(context))
                .build()
    }

    val courseComponent: CoursesComponent by lazy {
        appComponent
                .coursesComponentBuilder()
                .courseModule(CoursesModule())
                .build()
    }
}