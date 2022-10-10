package com.example.currencyconverter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.currencyconverter.data.network.Resource
import com.example.currencyconverter.data.repository.ConversionRepository
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val conversionRepository: ConversionRepository) : ViewModel() {
    fun getConversionRate(currentCurrency: String, targetCurrency: String, amount: String) =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(data = null))
            try {
                emit(
                    Resource.success(
                        data = conversionRepository.getConversionRate(
                            currentCurrency,
                            targetCurrency,
                            amount
                        )
                    )
                )
            } catch (exception: Exception) {
                emit(
                    Resource.error(
                        data = null,
                        message = exception.message
                    )
                )
            }
        }
}