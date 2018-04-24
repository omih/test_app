package com.example.mikhail.internal.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.example.data.network.ClientFactory
import com.example.data.network.api.Api
import com.example.data.network.api.ApiFactory
import com.example.data.storage.MainDatabase
import com.example.mikhail.internal.di.component.courses.CoursesComponent
import com.example.mikhail.internal.di.scope.AppScope
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module(subcomponents = [CoursesComponent::class])
class AppModule(private val context: Context) {
    private val cicerone = Cicerone.create()

    @Provides
    @AppScope
    fun provideNavigationHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides
    @AppScope
    fun provideAppRouter(): Router = cicerone.router

    @Provides
    @AppScope
    fun provideContext(): Context = context

    @Provides
    @AppScope
    fun provideDatabase(): MainDatabase = Room.databaseBuilder(context, MainDatabase::class.java, MainDatabase.DB_NAME).build()

    @Provides
    @AppScope
    fun provideApi(clientFactory: ClientFactory): Api = ApiFactory(clientFactory).access

    @Provides
    @AppScope
    fun provideNetworkClient() = ClientFactory()

}