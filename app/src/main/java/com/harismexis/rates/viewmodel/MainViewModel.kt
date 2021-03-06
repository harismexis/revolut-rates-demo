package com.harismexis.rates.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.harismexis.rates.extensions.convertToUiModels
import com.harismexis.rates.extensions.getErrorMessage
import com.harismexis.rates.model.Currency
import com.harismexis.rates.model.Currency.EUR
import com.harismexis.rates.model.CurrencyModel
import com.harismexis.rates.model.RateResponse
import com.harismexis.rates.model.amountAsString
import com.harismexis.rates.repository.RatesRepository
import com.harismexis.rates.util.BaseSchedulerProvider
import com.harismexis.rates.util.setSchedulersObservable
import com.harismexis.rates.util.setSchedulersSingle
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var rateRepository: RatesRepository,
    var schedulerProvider: BaseSchedulerProvider
) : ViewModel() {

    private var disposables: CompositeDisposable? = null

    companion object {
        private const val FIRST_RESPONDER_INITIAL_INPUT = "1"
        private const val ZERO_AMOUNT = 0.0f
        private const val INITIAL_RATE = 0.0f
    }

    private val TAG = MainViewModel::class.qualifiedName
    private var initialUiModels: List<CurrencyModel>
    private val uiModels: MutableList<CurrencyModel> = mutableListOf()
    private var baseAmount: Float? = FIRST_RESPONDER_INITIAL_INPUT.toFloat()
    private var firstResponderInput: String? = FIRST_RESPONDER_INITIAL_INPUT
    private var baseCurrency: String = EUR.key

    private val mRates = MutableLiveData<List<CurrencyModel>?>()
    val rates: LiveData<List<CurrencyModel>?>
        get() = mRates

    init {
        this.initialUiModels = createInitialUiModels()
        this.uiModels.addAll(initialUiModels)
    }

    fun updateBaseAmount(amount: String?) {
        amount?.let {
            this.firstResponderInput = amount
            baseAmount = if (amount.isNotBlank()) {
                amount.toFloat()
            } else {
                ZERO_AMOUNT
            }
        }
    }

    fun setBaseCurrency(currency: String?) {
        currency?.let {
            this.baseCurrency = currency
        }
    }

    fun updateFirstResponderData(newFirstResponder: CurrencyModel) {
        setBaseCurrency(newFirstResponder.currencyCode.key)
        updateBaseAmount(newFirstResponder.amountAsString())
    }

    fun getReorderedModels(newFirstResponder: CurrencyModel): List<CurrencyModel> {
        val items = ArrayList<CurrencyModel>()
        items.addAll(uiModels)
        items.remove(newFirstResponder)
        items.sortWith { object1, object2 ->
            object1.currencyCode.key
                .compareTo(object2.currencyCode.key)
        }
        items.add(0, newFirstResponder)
        for (item in items) {
            item.firstResponderInput = item.amountAsString()
        }
        return items
    }

    fun startRateUpdate() {
        if (isRateUpdateActive()) return
        disposables = CompositeDisposable()
        disposables?.add(getScheduleDisposable())
    }

    fun stopRateUpdate() {
        disposables?.dispose()
        disposables = null
    }

    private fun createInitialUiModels(): MutableList<CurrencyModel> {
        val list = ArrayList<CurrencyModel>()
        Currency.values().forEach {
            list.add(
                CurrencyModel(it, INITIAL_RATE, baseAmount, FIRST_RESPONDER_INITIAL_INPUT)
            )
        }
        return list
    }

    fun getInitialUiModels(): List<CurrencyModel> {
        return initialUiModels
    }

    fun scheduleAction(onComplete: () -> Unit) {
        disposables?.add(Completable.timer(500,
            TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
            .doOnComplete { onComplete() }
            .subscribe())
    }

    private fun getScheduleDisposable(): Disposable {
        return Observable.interval(0, 1, TimeUnit.SECONDS)
            .flatMapSingle { getRatesSingle() }
            .compose(setSchedulersObservable(schedulerProvider))
            .doOnError {
                stopRateUpdate()
            }
            .subscribe()
    }

    private fun getRatesSingle(): Single<RateResponse?> {
        return rateRepository.getRatesSingle(baseCurrency)
            .compose(setSchedulersSingle(schedulerProvider))
            .doOnSuccess {
                val models = it.convertToUiModels(baseCurrency, baseAmount, firstResponderInput)
                uiModels.clear()
                uiModels.addAll(models)
                mRates.value = uiModels
            }
            .doOnError {
                Log.d(TAG, it.getErrorMessage())
                mRates.value = null
            }

    }

    private fun isRateUpdateActive(): Boolean {
        disposables?.let {
            return !it.isDisposed
        }
        return false
    }

}