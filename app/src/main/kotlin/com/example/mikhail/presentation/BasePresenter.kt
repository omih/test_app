package com.example.mikhail.presentation

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


open class BasePresenter<T : MvpView> : MvpPresenter<T>() {
    private val compositeDisposable by lazy { CompositeDisposable() }

    fun dispose() {
        compositeDisposable.dispose()
    }

    fun clear() {
        compositeDisposable.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose()
    }

    fun safeSubscribe(action: () -> Disposable) {
        compositeDisposable.add(action())
    }
}