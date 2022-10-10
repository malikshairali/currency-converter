package com.example.currencyconverter.data.repository

import com.example.currencyconverter.data.api.ApiHelper

class ConversionRepository(private val apiHelper: ApiHelper) {
    suspend fun getConversionRate(currentCurrency: String, targetCurrency: String, amount: String) =
        apiHelper.getConversionRate(currentCurrency, targetCurrency, amount)
}