package com.example.currencyconverter.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.ui.adapter.CurrencyPickerAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CurrencyPickerDialog : BottomSheetDialogFragment() {

    private var onCurrencyChangeListener: ((currency: String) -> Unit)? = null
    private lateinit var currencyPickerAdapter: CurrencyPickerAdapter

    fun setOnCurrencyChangeListener(listener: (currency: String) -> Unit) {
        onCurrencyChangeListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_currency_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCurrencyAdapter()
        initRecyclerView(view)
        initListeners()
    }

    private fun initRecyclerView(view: View) {
        val rvCurrencies = view.findViewById<RecyclerView>(R.id.rv_currency)
        rvCurrencies.adapter = currencyPickerAdapter
    }

    private fun initListeners() {
        currencyPickerAdapter.setOnItemClickListener { currency ->
            onCurrencyChangeListener?.invoke(currency)
            dismiss()
        }
    }

    private fun initCurrencyAdapter() {
        currencyPickerAdapter = CurrencyPickerAdapter(
            requireArguments().getStringArray(KEY_CURRENCIES)
                ?: resources.getStringArray(R.array.currencies),
            requireArguments().getString(KEY_CURRENCY, String())
        )
    }

    companion object {
        val TAG: String = CurrencyPickerDialog::class.java.simpleName
        private const val KEY_CURRENCY = "KEY_CURRENCY"
        private const val KEY_CURRENCIES = "KEY_CURRENCIES"

        fun newInstance(currency: String, currencies: List<String>?): CurrencyPickerDialog {
            return CurrencyPickerDialog().apply {
                arguments = Bundle().apply {
                    putString(KEY_CURRENCY, currency)
                    putStringArray(KEY_CURRENCIES, currencies?.toTypedArray())
                }
            }
        }
    }
}