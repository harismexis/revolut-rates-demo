package com.example.rates.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.rates.model.Currency
import com.example.rates.model.CurrencyModel
import com.example.rates.model.RateResponse
import com.example.rates.repository.RatesRepository
import com.example.rates.util.TrampolineSchedulerProvider
import com.example.rates.util.getFakeResponseAud
import com.example.rates.util.getFakeResponseEuro
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations


@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var mockRatesRepository: RatesRepository

    @Mock
    lateinit var mockRateResponse: RateResponse

    @Mock
    lateinit var observer: Observer<List<CurrencyModel>?>

    private var BASE_CURRENCY_EUR: String = Currency.EUR.key
    private var BASE_CURRENCY_AUD: String = Currency.AUD.key
    private var BASE_CURRENCY_BGN: String = Currency.BGN.key

    private lateinit var mainViewModel: MainViewModel

    private var testSchedulerProvider = TrampolineSchedulerProvider()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        initClassUnderTest()
    }

    private fun initClassUnderTest() {
        mainViewModel = MainViewModel(mockRatesRepository)
        mainViewModel.setSchedulerProvider(testSchedulerProvider)
        mainViewModel.setBaseCurrency(BASE_CURRENCY_EUR)
        mainViewModel.rates.observeForever(observer)
    }

    private fun setupMocks() {
        `when`(mockRatesRepository.getRatesSingle(any())).thenReturn(
            Single.just(getFakeResponseEuro())
        )
    }

    @Test
    fun rateUpdateStartsAndStops_networkCallMadeAndDataUpdatedExpectedTimes() {
        // when
        mainViewModel.startRateUpdate()
        Thread.sleep(1500)
        mainViewModel.stopRateUpdate()

        // then
        verify(mockRatesRepository, times(2)).getRatesSingle(BASE_CURRENCY_EUR)
        verify(observer, times(2)).onChanged(mainViewModel.rates.value)
        Assert.assertEquals(mainViewModel.rates.value!![0].currencyCode.key, BASE_CURRENCY_EUR)
    }

    @Test
    fun rateUpdateStartsAndStops_firstUiModelIsTheExpectedFirstResponder() {
        // given
        mainViewModel.setBaseCurrency(BASE_CURRENCY_EUR)

        // when
        mainViewModel.startRateUpdate()
        Thread.sleep(200)
        mainViewModel.stopRateUpdate()

        // then
        verify(mockRatesRepository, times(1)).getRatesSingle(BASE_CURRENCY_EUR)
        verify(observer, times(1)).onChanged(mainViewModel.rates.value)
        Assert.assertEquals(mainViewModel.rates.value!![0].currencyCode.key, BASE_CURRENCY_EUR)
        Assert.assertEquals(mainViewModel.rates.value!![1].currencyCode.key, BASE_CURRENCY_AUD)
    }

    @Test
    fun rateUpdateReturnsInvalidResponse_dataIsUpdatedToNull() {
        // given
        `when`(mockRatesRepository.getRatesSingle(any())).thenReturn(
            Single.just(mockRateResponse)
        )

        // when
        mainViewModel.startRateUpdate()
        Thread.sleep(500)
        mainViewModel.stopRateUpdate()

        // then
        verify(mockRatesRepository, times(1)).getRatesSingle(BASE_CURRENCY_EUR)
        verify(observer, times(1)).onChanged(ArrayList())
        Assert.assertTrue(mainViewModel.rates.value!!.isEmpty())
    }

    @Test
    fun baseCurrencyChanges_networkCallCurrencyParameterIsUpdatedAndDataIsUpdated() {
        // given
        `when`(mockRatesRepository.getRatesSingle(any())).thenReturn(
            Single.just(getFakeResponseEuro())
        )

        // when
        mainViewModel.startRateUpdate()
        Thread.sleep(200)
        mainViewModel.stopRateUpdate()

        mainViewModel.setBaseCurrency(BASE_CURRENCY_AUD)
        `when`(mockRatesRepository.getRatesSingle(any())).thenReturn(
            Single.just(getFakeResponseAud())
        )

        mainViewModel.startRateUpdate()
        Thread.sleep(200)
        mainViewModel.stopRateUpdate()

        // then
        verify(mockRatesRepository, times(1)).getRatesSingle(BASE_CURRENCY_EUR)
        verify(mockRatesRepository, times(1)).getRatesSingle(BASE_CURRENCY_AUD)
        Assert.assertEquals(mainViewModel.rates.value!![0].currencyCode.key, BASE_CURRENCY_AUD)
        Assert.assertEquals(mainViewModel.rates.value!![1].currencyCode.key, BASE_CURRENCY_BGN)
    }

}