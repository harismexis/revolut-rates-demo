package com.harismexis.rates.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.harismexis.rates.extensions.convertToUiModels
import com.harismexis.rates.repository.RatesRepository
import com.harismexis.rates.util.TrampolineSchedulerProvider
import com.harismexis.rates.util.getFakeResponseAud
import com.harismexis.rates.util.getFakeResponseEuro
import com.harismexis.rates.model.*
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

    companion object {
        const val EXPECTED_NUMBER_OF_ITEMS = 32
        const val USER_INPUT = "135.040"
    }

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

    private lateinit var mainViewModel: MainViewModel
    private var testSchedulerProvider = TrampolineSchedulerProvider()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        setupMocks()
        initClassUnderTest()
    }

    private fun initClassUnderTest() {
        mainViewModel = MainViewModel(mockRatesRepository, testSchedulerProvider)
        mainViewModel.setBaseCurrency(BASE_CURRENCY_EUR)
        mainViewModel.updateBaseAmount(USER_INPUT)
        mainViewModel.rates.observeForever(observer)
    }

    private fun setupMocks() {
        `when`(mockRatesRepository.getRatesSingle(any())).thenReturn(
            Single.just(getFakeResponseEuro())
        )
    }

    @Test
    fun rateUpdateStartsAndStops_networkCallAndDataUpdateHappenExpectedTimes() {
        // when
        mainViewModel.startRateUpdate()
        Thread.sleep(1500)
        mainViewModel.stopRateUpdate()

        // then
        verify(mockRatesRepository, times(2)).getRatesSingle(BASE_CURRENCY_EUR)
        verify(observer, times(2)).onChanged(mainViewModel.rates.value)
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
    }

    @Test
    fun rateUpdateReturnsInvalidResponse_uiModelsListIsEmpty() {
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
    fun rateUpdateRestartsAndFirstResponderChanges_dataIsTheExpected() {
        // given
        val fakeResponseEuro = getFakeResponseEuro()
        `when`(mockRatesRepository.getRatesSingle(any())).thenReturn(
            Single.just(fakeResponseEuro)
        )
        var expectedModels = fakeResponseEuro.convertToUiModels(
            BASE_CURRENCY_EUR,
            USER_INPUT.toFloat(),
            USER_INPUT
        )

        // when
        mainViewModel.startRateUpdate()
        Thread.sleep(200)
        mainViewModel.stopRateUpdate()

        // then
        verify(mockRatesRepository, times(1)).getRatesSingle(BASE_CURRENCY_EUR)
        assertUiModelsAreTheExpected(expectedModels)

        // First Responder changes to AUD
        val newBaseAmount = getModelForCurrency(Currency.AUD.key, expectedModels)!!.amountAsString()
        val fakeResponseAud = getFakeResponseAud()
        `when`(mockRatesRepository.getRatesSingle(any())).thenReturn(
            Single.just(fakeResponseAud)
        )
        expectedModels = fakeResponseAud.convertToUiModels(
            BASE_CURRENCY_AUD,
            newBaseAmount.toFloat(),
            newBaseAmount
        )
        mainViewModel.setBaseCurrency(BASE_CURRENCY_AUD)
        mainViewModel.updateBaseAmount(newBaseAmount)

        // when
        mainViewModel.startRateUpdate()
        Thread.sleep(200)
        mainViewModel.stopRateUpdate()

        // then
        verify(mockRatesRepository, times(1)).getRatesSingle(BASE_CURRENCY_AUD)
        assertUiModelsAreTheExpected(expectedModels)
    }

    private fun assertUiModelsListHasExpectedSize() {
        Assert.assertTrue(mainViewModel.rates.value!!.size == EXPECTED_NUMBER_OF_ITEMS)
    }

    private fun getModelForCurrency(key: String, models: List<CurrencyModel>): CurrencyModel? {
        for (model in models) {
            if (model.currencyCode.key == key) return model
        }
        return null
    }

    private fun assertUiModelsAreTheExpected(expectedUiModels: List<CurrencyModel>) {
        assertUiModelsListHasExpectedSize()

        expectedUiModels.forEachIndexed { index, expectedUiModel ->

            Assert.assertEquals(
                expectedUiModel.currencyCode.key,
                mainViewModel.rates.value!![index].currencyCode.key
            )

            Assert.assertEquals(
                expectedUiModel.currencyCode.description,
                mainViewModel.rates.value!![index].currencyCode.description
            )

            Assert.assertEquals(
                expectedUiModel.currencyCode.id,
                mainViewModel.rates.value!![index].currencyCode.id
            )

            Assert.assertEquals(
                expectedUiModel.getAmount(),
                mainViewModel.rates.value!![index].getAmount()
            )

            Assert.assertEquals(
                expectedUiModel.amountAsString(),
                mainViewModel.rates.value!![index].amountAsString()
            )

            Assert.assertEquals(
                expectedUiModel.rate,
                mainViewModel.rates.value!![index].rate
            )

            Assert.assertEquals(
                expectedUiModel.firstResponderInput,
                mainViewModel.rates.value!![index].firstResponderInput
            )

        }

    }

}