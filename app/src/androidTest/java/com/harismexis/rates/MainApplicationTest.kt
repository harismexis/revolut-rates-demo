package com.harismexis.rates

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.*

@RunWith(AndroidJUnit4::class)
class MainApplicationTest {

    @Test
    fun appContext_hasExpectedPackageName() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.rates", appContext.packageName)
    }

}