package com.example.mikhail.core.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.example.data.network.ClientFactory
import com.example.data.storage.MainDatabase
import com.example.mikhail.core.di.component.courses.CoursesComponent
import com.example.mikhail.core.di.scope.AppScope
import dagger.Module
import dagger.Provides

@Module(subcomponents = [CoursesComponent::class])
class AppModule(private val context: Context) {

    @Provides
    @AppScope
    fun provideContext(): Context = context

    @Provides
    @AppScope
    fun provideDatabase(): MainDatabase =
        Room.databaseBuilder(context, MainDatabase::class.java, MainDatabase.DB_NAME).build()

    @Provides
    @AppScope
    fun provideNetworkClient() = ClientFactory()

    @Provides
    @AppScope
    fun provideNetworkKtorClient(clientFactory: ClientFactory) = clientFactory.create()
}