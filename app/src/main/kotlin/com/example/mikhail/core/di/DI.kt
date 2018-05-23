package com.example.mikhail.core.di

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