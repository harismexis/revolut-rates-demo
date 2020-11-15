package com.example.rates.di

import com.example.rates.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {

    @ContributesAndroidInjector
    abstract fun accountActivity(): MainActivity

}
