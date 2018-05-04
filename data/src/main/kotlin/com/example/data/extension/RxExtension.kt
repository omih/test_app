package com.example.data.extension

import com.example.data.scheduler.SchedulerProvider
import io.reactivex.*
import timber.log.Timber

//region Single
fun <T> Single<T>.schedulersIoToMain(schedulers: SchedulerProvider): Single<T> {
    return subscribeOn(schedulers.io()).observeOn(schedulers.ui())
}

fun <T> Single<T>.logError(): Single<T> {
    return doOnError { Timber.e(it) }
}
//endregion

//region Observable
fun <T> Observable<T>.schedulersIoToMain(schedulers: SchedulerProvider): Observable<T> {
    return subscribeOn(schedulers.io()).observeOn(schedulers.ui())
}

fun <T> Observable<T>.logError(): Observable<T> {
    return doOnError { Timber.e(it) }
}
//endregion

//region Flowable
fun <T> Flowable<T>.schedulersIoToMain(schedulers: SchedulerProvider): Flowable<T> {
    return subscribeOn(schedulers.io()).observeOn(schedulers.ui())
}

fun <T> Flowable<T>.logError(): Flowable<T> {
    return doOnError { Timber.e(it) }
}
//endregion

//region Completable
fun Completable.schedulersIoToMain(schedulers: SchedulerProvider): Completable {
    return subscribeOn(schedulers.io()).observeOn(schedulers.ui())
}

fun Completable.logError(): Completable {
    return doOnError { Timber.e(it) }
}
//endregion

//region Maybe
fun <T> Maybe<T>.schedulersIoToMain(schedulers: SchedulerProvider): Maybe<T> {
    return subscribeOn(schedulers.io()).observeOn(schedulers.ui())
}

fun <T> Maybe<T>.logError(): Maybe<T> {
    return doOnError { Timber.e(it) }
}
//endregion