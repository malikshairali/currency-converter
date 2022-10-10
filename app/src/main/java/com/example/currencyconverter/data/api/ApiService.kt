package com.example.currencyconverter.data.api

import com.example.currencyconverter.data.model.ConversionResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {
    @GET("pair/{current}/{target}/{amount}")
    suspend fun getConversionRate(
        @Path("current") currentCurrency: String,
        @Path("target") targetCurrency: String,
        @Path("amount") amount: String
    ): ConversionResponse
}