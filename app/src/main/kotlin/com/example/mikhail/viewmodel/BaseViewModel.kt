package com.example.mikhail.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.mikhail.core.exception.Failure

abstract class BaseViewModel : ViewModel() {
    var failure: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }
}