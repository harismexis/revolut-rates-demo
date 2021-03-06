package com.harismexis.rates.di

import com.harismexis.rates.MainApplication
import com.harismexis.rates.viewmodel.factory.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingsModule::class,
        ViewModelModule::class,
        MainModule::class]
)
interface MainComponent : AndroidInjector<MainApplication> {

}