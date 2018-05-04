package com.example.data.scheduler

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

object AppSchedulerProviderTest : SchedulerProvider{

    override fun ui(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun io(): Scheduler {
        return Schedulers.io()
    }
}