package com.harismexis.rates.util

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class BaseSchedulerProvider {
    abstract fun io(): Scheduler
    abstract fun computation(): Scheduler
    abstract fun ui(): Scheduler
}

class SchedulerProvider @Inject constructor() : BaseSchedulerProvider() {
    override fun computation() = Schedulers.computation()
    override fun ui() = AndroidSchedulers.mainThread()
    override fun io() = Schedulers.io()
}

class TrampolineSchedulerProvider @Inject constructor(): BaseSchedulerProvider() {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}