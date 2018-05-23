package com.example.mikhail.core.extension

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import com.example.mikhail.core.exception.Failure

fun <L : LiveData<T>, T : Any> LifecycleOwner.success(liveData: L, body: (T?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: (Failure?) -> Unit) {
    liveData.observe(this, Observer(body))
}

fun <L : LiveData<Failure>> LifecycleOwner.failure(liveData: L, body: () -> Unit) {
    liveData.observe(this, Observer { body.invoke() })
}