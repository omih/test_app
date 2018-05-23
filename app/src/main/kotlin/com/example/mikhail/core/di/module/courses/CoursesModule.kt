package com.example.mikhail.core.di.module.courses

import com.example.data.network.api.Api
import com.example.data.repository.CoursesDataRepository
import com.example.data.scheduler.SchedulerProvider
import com.example.data.storage.MainDatabase
import com.example.mikhail.core.di.scope.UserScope
import com.example.model.repository.CoursesRepository
import com.example.model.usecase.CoursesUseCase
import dagger.Module
import dagger.Provides

@Module
class CoursesModule {

    @UserScope
    @Provides
    fun provideCoursesRepository(
        api: Api,
        db: MainDatabase,
        schedulers: SchedulerProvider
    ): CoursesRepository = CoursesDataRepository(api, db, schedulers)

    @UserScope
    @Provides
    fun provideCoursesUseCase(coursesRepository: CoursesRepository): CoursesUseCase =
        CoursesUseCase(coursesRepository)

}