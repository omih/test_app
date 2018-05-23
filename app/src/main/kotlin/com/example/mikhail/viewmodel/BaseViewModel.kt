package com.example.mikhail.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.mikhail.core.exception.Failure
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {
    var failure: MutableLiveData<Failure> = MutableLiveData()

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

    override fun onCleared() {
        dispose()
    }

    fun safeSubscribe(action: () -> Disposable) {
        compositeDisposable.add(action())
    }

    fun safeSingleSubscribe(action: () -> Disposable) {
        singleDisposable.clear()
        singleDisposable.add(action())
    }

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}