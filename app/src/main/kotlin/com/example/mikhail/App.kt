package com.example.mikhail

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.data.storage.MainDatabase
import com.example.mikhail.core.di.DI
import javax.inject.Inject

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

    @Inject
    internal lateinit var db: MainDatabase

    override fun onCreate() {
        super.onCreate()

        context = this

        buildDependencyGraph()
    }

    override fun onTerminate() {
        db.close()
        super.onTerminate()
    }

    private fun buildDependencyGraph() {
        with(DI) {
            init(context)
            componentManager.appComponent.inject(this@App)
        }
    }
}