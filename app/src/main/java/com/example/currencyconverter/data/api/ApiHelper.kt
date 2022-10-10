package com.example.currencyconverter.data.api

class ApiHelper(private val apiService: ApiService) {
    suspend fun getConversionRate(currentCurrency: String, targetCurrency: String, amount: String) =
        apiService.getConversionRate(currentCurrency, targetCurrency, amount)
}