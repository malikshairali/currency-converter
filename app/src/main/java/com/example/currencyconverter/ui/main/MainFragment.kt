package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.MainViewModel
import com.example.currencyconverter.R
import com.example.currencyconverter.data.api.ApiHelper
import com.example.currencyconverter.data.api.RetrofitBuilder
import com.example.currencyconverter.data.model.ConversionResponse
import com.example.currencyconverter.data.network.Status
import com.example.currencyconverter.manager.FirebaseManager
import com.example.currencyconverter.ui.base.ViewModelFactory
import com.example.currencyconverter.util.Constants
import com.example.currencyconverter.util.DialogUtils

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var progressBar: ProgressBar
    private lateinit var btnCurrentCurrency: Button
    private lateinit var btnTargetCurrency: Button
    private lateinit var etAmount: EditText

    private val firebaseManager = FirebaseManager()
    private var currencies: List<String>? = null
    private var timer: Long? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupView(view)
        initFirebaseListeners()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this, ViewModelFactory(ApiHelper(RetrofitBuilder.apiService), requireContext())
        )[MainViewModel::class.java]
    }

    private fun setupView(view: View) {
        val btnCalculate = view.findViewById<Button>(R.id.btn_calculate)
        btnCalculate?.setOnClickListener {
            getConversionRate()
        }

        btnTargetCurrency = view.findViewById<Button>(R.id.btn_target_currency)
        btnTargetCurrency.setOnClickListener {
            val picker = DialogUtils.showCurrencyPicker(
                childFragmentManager, btnTargetCurrency.text.toString(), currencies
            )
            picker.setOnCurrencyChangeListener { currency ->
                btnTargetCurrency.text = currency
            }
        }

        btnCurrentCurrency = view.findViewById<Button>(R.id.btn_current_currency)
        btnCurrentCurrency.setOnClickListener {
            val picker = DialogUtils.showCurrencyPicker(
                childFragmentManager, btnCurrentCurrency.text.toString(), currencies
            )
            picker.setOnCurrencyChangeListener { currency ->
                btnCurrentCurrency.text = currency
            }
        }

        etAmount = view.findViewById(R.id.et_amount)
        progressBar = view.findViewById(R.id.progress_bar)
    }

    private fun initFirebaseListeners() {
        firebaseManager.setOnUpdateCountDownTimerListener { timer ->
            this.timer = timer
        }
        firebaseManager.setOnUpdateCurrenciesListener { currencies ->
            this.currencies = currencies
        }
    }

    private fun getConversionRate() {
        val enteredAmount = etAmount.text.toString()

        // Check for same currency selection
        if (btnCurrentCurrency.text == btnTargetCurrency.text) {
            showToast(getString(R.string.error_same_currencies_selected))
            return
        }

        // Check for valid entered amount
        if (enteredAmount.isEmpty() || enteredAmount.toDouble() <= 0) {
            etAmount.error = getString(R.string.error_invalid_value)
            return
        }

        viewModel.getConversionRate(
            btnCurrentCurrency.text.toString(),
            btnTargetCurrency.text.toString(),
            etAmount.text.toString()
        ).observe(viewLifecycleOwner) {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        setProgressBarVisibility(false)
                        navigateToConvertFragment(resource.data)
                    }
                    Status.ERROR -> {
                        setProgressBarVisibility(false)
                        showToast(resource.message)
                    }
                    Status.LOADING -> {
                        setProgressBarVisibility(true)
                    }
                }
            }
        }
    }

    private fun navigateToConvertFragment(conversionResponse: ConversionResponse?) {
        val action = MainFragmentDirections.actionMainFragmentToConverterFragment(
            currentValue = getString(
                R.string.currency_value, etAmount.text.toString(), btnCurrentCurrency.text
            ),
            targetValue = getString(
                R.string.currency_value,
                String.format("%.2f", conversionResponse?.conversionResult),
                btnTargetCurrency.text
            ),
            conversionRate = conversionResponse?.conversionRate.toString(),
            timer ?: Constants.COUNT_DOWN_TIMER
        )
        findNavController().navigate(action)
    }

    private fun setProgressBarVisibility(visibility: Boolean) {
        progressBar.isVisible = visibility
    }

    private fun showToast(message: String?) {
        Toast.makeText(
            requireContext(), message, Toast.LENGTH_LONG
        ).show()
    }
}