package com.example.rates.di

import androidx.annotation.NonNull
import com.example.rates.util.BaseSchedulerProvider
import com.example.rates.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @NonNull
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }
}