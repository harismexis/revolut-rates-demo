package com.harismexis.rates.di

import com.harismexis.rates.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}
