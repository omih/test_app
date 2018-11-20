package com.example.mikhail.core.di.module.courses

import com.example.data.repository.CoursesDataRepository
import com.example.data.storage.MainDatabase
import com.example.mikhail.core.di.scope.UserScope
import com.example.model.repository.CoursesRepository
import com.example.model.usecase.CoursesUseCase
import dagger.Module
import dagger.Provides
import io.ktor.client.HttpClient

@Module
class CoursesModule {

    @UserScope
    @Provides
    fun provideCoursesRepository(
        network: HttpClient,
        db: MainDatabase
    ): CoursesRepository = CoursesDataRepository(network, db)

    @UserScope
    @Provides
    fun provideCoursesUseCase(coursesRepository: CoursesRepository): CoursesUseCase =
        CoursesUseCase(coursesRepository)

}