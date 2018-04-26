package com.example.mikhail.presentation.presenter

import com.example.mikhail.cicerone.Screens
import com.example.mikhail.internal.di.DI
import com.example.mikhail.presentation.BasePresenter
import com.example.mikhail.presentation.view.SingleView
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class SinglePresenter : BasePresenter<SingleView>() {

    @Inject
    protected lateinit var router: Router

    init {
        DI.componentManager.appComponent.inject(this)

        router.newRootScreen(Screens.COURSES)
    }
}