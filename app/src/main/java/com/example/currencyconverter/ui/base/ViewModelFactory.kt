package com.example.currencyconverter.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.data.api.ApiHelper
import com.example.currencyconverter.data.repository.ConversionRepository

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java))
            return MainViewModel(ConversionRepository(apiHelper)) as T
        throw IllegalArgumentException("Unknown class name")
    }
}