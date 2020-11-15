package com.example.rates.di

import androidx.annotation.NonNull
import com.example.rates.repository.RatesRepository
import com.example.rates.viewmodel.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @NonNull
    @Provides
    fun provideMainViewModel(
        ratesRepository: RatesRepository
    ): MainViewModel {
        return MainViewModel(ratesRepository)
    }
}