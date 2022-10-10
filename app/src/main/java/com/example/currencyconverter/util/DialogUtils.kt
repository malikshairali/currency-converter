package com.example.currencyconverter.util

import androidx.fragment.app.FragmentManager
import com.example.currencyconverter.ui.dialog.CurrencyPickerDialog

object DialogUtils {
    fun showCurrencyPicker(
        fragmentManager: FragmentManager,
        currency: String,
        currencies: List<String>?
    ): CurrencyPickerDialog {
        val dialog = CurrencyPickerDialog.newInstance(currency, currencies)
        dialog.show(fragmentManager, CurrencyPickerDialog.TAG)
        return dialog
    }
}