package com.example.currencyconverter.util

object DateTimeUtils {

    fun hrsDifferenceWithTimeNow(pastMillis: Long): Int? {
        return if (pastMillis > 0)
            ((System.currentTimeMillis() - pastMillis)/Constants.MILLIS_IN_HR).toInt()
        else
            null
    }
}