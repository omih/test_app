package com.example.mikhail.internal.di

import android.annotation.SuppressLint
import android.content.Context

object DI {
    @SuppressLint("StaticFieldLeak")
    lateinit var componentManager: ComponentManager
        private set

    fun init(context: Context) {
        componentManager = ComponentManager(context)
    }
}