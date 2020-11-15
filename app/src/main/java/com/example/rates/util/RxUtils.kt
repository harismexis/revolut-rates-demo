package com.example.rates.util

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.Single
import io.reactivex.SingleTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> setSchedulersSingle(): SingleTransformer<T, T> {
    return SingleTransformer { upstream: Single<T> ->
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> setSchedulersObservable(): ObservableTransformer<T, T> {
    return ObservableTransformer { observable: Observable<T> ->
        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}