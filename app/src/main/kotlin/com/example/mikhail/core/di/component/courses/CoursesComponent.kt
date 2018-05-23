package com.example.mikhail.core.di.component.courses

import com.example.mikhail.core.di.module.courses.CoursesModule
import com.example.mikhail.core.di.scope.UserScope
import com.example.mikhail.ui.fragment.courses.AllCoursesFragment
import com.example.mikhail.ui.fragment.courses.FavoriteCoursesFragment
import dagger.Subcomponent

@UserScope
@Subcomponent(modules = [CoursesModule::class])
interface CoursesComponent {
    fun inject(allCoursesFragment: AllCoursesFragment)
    fun inject(favoriteCoursesFragment: FavoriteCoursesFragment)

    @Subcomponent.Builder
    interface Builder {
        fun courseModule(coursesModule: CoursesModule): Builder
        fun build(): CoursesComponent
    }
}