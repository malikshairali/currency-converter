package com.example.currencyconverter.data.model

import com.google.gson.annotations.SerializedName

data class ConversionResponse(
    var result: String,

    @SerializedName("conversion_rate")
    var conversionRate: Float,

    @SerializedName("conversion_result")
    var conversionResult: Float
)
