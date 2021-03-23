package com.harismexis.rates.di

import androidx.annotation.NonNull
import com.harismexis.rates.util.BaseSchedulerProvider
import com.harismexis.rates.util.SchedulerProvider
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