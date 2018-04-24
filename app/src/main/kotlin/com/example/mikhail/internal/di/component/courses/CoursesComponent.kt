package com.example.mikhail.internal.di.component.courses

import com.example.mikhail.internal.di.module.courses.CoursesModule
import com.example.mikhail.internal.di.scope.UserScope
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [CoursesModule::class])
interface CoursesComponent {

    @Subcomponent.Builder
    interface Builder {
        fun courseModule(coursesModule: CoursesModule): Builder
        fun build(): CoursesComponent
    }
}