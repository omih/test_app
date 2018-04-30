package com.example.mikhail.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BasePresenter<T : MvpView> : MvpPresenter<T>() {
    private val compositeDisposable by lazy { CompositeDisposable() }
    private val singleDisposable by lazy { CompositeDisposable() }

    fun dispose() {
        compositeDisposable.dispose()
        singleDisposable.dispose()
    }

    fun clear() {
        compositeDisposable.clear()
        singleDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    fun safeSubscribe(action: () -> Disposable) {
        compositeDisposable.add(action())
    }

    fun safeSingleSubscribe(action: () -> Disposable) {
        singleDisposable.clear()
        singleDisposable.add(action())
    }
}