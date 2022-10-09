package com.example.currencyconverter.util

import androidx.fragment.app.FragmentManager
import com.example.currencyconverter.ui.dialog.CurrencyPickerDialog

object DialogUtils {
    fun showCurrencyPicker(
        fragmentManager: FragmentManager,
        currency: String
    ): CurrencyPickerDialog {
        val dialog = CurrencyPickerDialog.newInstance(currency)
        dialog.show(fragmentManager, CurrencyPickerDialog.TAG)
        return dialog
    }
}