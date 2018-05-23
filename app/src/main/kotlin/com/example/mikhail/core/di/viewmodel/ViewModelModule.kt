package com.example.mikhail.core.di.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.mikhail.viewmodel.AllCoursesViewModel
import com.example.mikhail.viewmodel.FavoriteCoursesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    abstract fun factory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AllCoursesViewModel::class)
    abstract fun allCoursesViewModel(viewModel: AllCoursesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteCoursesViewModel::class)
    abstract fun favoriteCoursesViewModel(viewModel: FavoriteCoursesViewModel): ViewModel
}