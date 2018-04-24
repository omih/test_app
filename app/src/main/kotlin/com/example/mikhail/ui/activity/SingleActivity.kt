package com.example.mikhail.ui.activity

import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.example.mikhail.R
import com.example.mikhail.cicerone.MainNavigator
import com.example.mikhail.internal.di.DI
import com.example.mikhail.presentation.presenter.SinglePresenter
import com.example.mikhail.presentation.view.SingleView
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class SingleActivity : MvpAppCompatActivity(), SingleView {

    private val navigator: Navigator by lazy {
        MainNavigator(this@SingleActivity,
                R.id.container)
    }

    @Inject
    lateinit var navigationHolder: NavigatorHolder

    @InjectPresenter
    internal lateinit var presenter: SinglePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single)

        DI.componentManager.appComponent.inject(this)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigationHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigationHolder.removeNavigator()
    }

}