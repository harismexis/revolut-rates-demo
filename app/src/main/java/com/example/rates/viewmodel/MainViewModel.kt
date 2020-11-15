package com.example.rates.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rates.extensions.convertToUiModels
import com.example.rates.extensions.getErrorMessage
import com.example.rates.model.CurrencyCode.EUR
import com.example.rates.model.CurrencyModel
import com.example.rates.model.RateResponse
import com.example.rates.repository.RatesRepository
import com.example.rates.util.setSchedulersObservable
import com.example.rates.util.setSchedulersSingle
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var rateRepository: RatesRepository
) : ViewModel() {

    private var disposables: CompositeDisposable? = null

    companion object {
        private const val INITIAL_BASE_AMOUNT = 1.0f
        private const val ZERO_AMOUNT = 0.0f
    }

    private val TAG = MainViewModel::class.qualifiedName
    private val uiModels = ArrayList<CurrencyModel>()
    private var baseAmount: Float? = INITIAL_BASE_AMOUNT
    private var baseCurrency: String = EUR.key

    private val mRates = MutableLiveData<List<CurrencyModel>?>()
    val rates: LiveData<List<CurrencyModel>?>
        get() = mRates

    fun startRateUpdate() {
        if (isRateUpdateActive()) return
        disposables = CompositeDisposable()
        disposables?.add(getScheduleDisposable())
    }

    fun stopRateUpdate() {
        disposables?.dispose()
        disposables = null
    }

    fun setBaseAmount(amount: Float?) {
        amount?.let {
            this.baseAmount = amount
        }
    }

    fun filterResponderText(text: String): String {
        var amount = ZERO_AMOUNT.toString()
        if (text.isNotBlank()) {
            amount = text
        }
        return amount
    }

    fun setBaseCurrency(currency: String?) {
        currency?.let {
            this.baseCurrency = currency
        }
    }

    private fun getRatesSingle(): Single<RateResponse?> {
        return rateRepository.getRatesSingle(baseCurrency)
            .compose(setSchedulersSingle())
            .doOnSuccess {
                val models = it.convertToUiModels(baseCurrency, baseAmount)
                uiModels.clear()
                uiModels.addAll(models)
                mRates.value = uiModels
            }
            .doOnError {
                Log.d(TAG, it.getErrorMessage())
                mRates.value = null
            }
    }

    private fun getScheduleDisposable(): Disposable {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMapSingle { getRatesSingle() }
            .compose(setSchedulersObservable())
            .subscribe()
    }

    private fun isRateUpdateActive(): Boolean {
        disposables?.let {
            return !it.isDisposed
        }
        return false
    }

}