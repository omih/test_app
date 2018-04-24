package com.example.data.extension

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

//region Single
fun <T> Single<T>.schedulersIoToMain(): Single<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.logError(): Single<T> {
    return doOnError { Timber.e(it) }
}
//endregion

//region Observable
fun <T> Observable<T>.schedulersIoToMain(): Observable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Observable<T>.logError(): Observable<T> {
    return doOnError { Timber.e(it) }
}
//endregion

//region Flowable
fun <T> Flowable<T>.schedulersIoToMain(): Flowable<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Flowable<T>.logError(): Flowable<T> {
    return doOnError { Timber.e(it) }
}
//endregion

//region Completable
fun Completable.schedulersIoToMain(): Completable {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun Completable.logError(): Completable {
    return doOnError { Timber.e(it) }
}
//endregion

//region Maybe
fun <T> Maybe<T>.schedulersIoToMain(): Maybe<T> {
    return subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
}

fun <T> Maybe<T>.logError(): Maybe<T> {
    return doOnError { Timber.e(it) }
}
//endregion