package com.example.rates.di

import androidx.annotation.NonNull
import com.example.rates.repository.RatesRepository
import com.example.rates.util.BaseSchedulerProvider
import com.example.rates.util.SchedulerProvider
import com.example.rates.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @NonNull
    @Provides
    fun provideMainViewModel(
        ratesRepository: RatesRepository,
        schedulerProvider: BaseSchedulerProvider
    ): MainViewModel {
        return MainViewModel(ratesRepository, schedulerProvider)
    }

    @NonNull
    @Provides
    fun provideSchedulerProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }
}