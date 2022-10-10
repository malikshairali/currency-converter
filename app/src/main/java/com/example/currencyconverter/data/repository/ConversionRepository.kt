package com.example.currencyconverter.data.repository

import android.content.Context
import com.example.currencyconverter.data.api.ApiHelper
import com.example.currencyconverter.data.model.ConversionResponse
import com.example.currencyconverter.manager.PreferenceManager
import com.example.currencyconverter.util.DateTimeUtils

class ConversionRepository(private val apiHelper: ApiHelper, context: Context) {
    private val preferenceManager = PreferenceManager(context)

    suspend fun getConversionRate(
        currentCurrency: String,
        targetCurrency: String,
        amount: String
    ): ConversionResponse {

        val timeDifference =
            DateTimeUtils.hrsDifferenceWithTimeNow(preferenceManager.getLastRefreshedTime())
        val conversionRate =
            preferenceManager.getCurrencyConversionRate(currentCurrency + targetCurrency)

        return if (timeDifference != null && timeDifference < 5 && conversionRate > 0) {
            ConversionResponse("success", conversionRate, amount.toFloat() * conversionRate)
        } else {
            val response = apiHelper.getConversionRate(currentCurrency, targetCurrency, amount)
            if (response.result == "success") {
                preferenceManager.setLastRefreshedTime(System.currentTimeMillis())
                preferenceManager.setCurrencyConversionRate(
                    currentCurrency + targetCurrency,
                    response.conversionRate
                )
            }
            response
        }
    }
}