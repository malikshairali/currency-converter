package com.example.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class ConversionResponse(
    val result: String,

    @SerializedName("conversion_rate")
    val conversionRate: Double,

    @SerializedName("conversion_result")
    val conversionResult: Double
)
