package com.harismexis.rates

import com.harismexis.rates.di.DaggerMainComponent
import com.harismexis.rates.di.MainComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import io.reactivex.plugins.RxJavaPlugins
import javax.inject.Inject

class MainApplication : DaggerApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    private lateinit var mainComponent: MainComponent

    override fun onCreate() {
        super.onCreate()
        initRxErrorHandler()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return dispatchingAndroidInjector
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        mainComponent = DaggerMainComponent.builder().build()
        mainComponent.inject(this)
        return mainComponent
    }

    private fun initRxErrorHandler() {
        RxJavaPlugins.setErrorHandler { ex: Throwable ->

        }
    }

}