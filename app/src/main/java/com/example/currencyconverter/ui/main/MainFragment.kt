package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.currencyconverter.R
import com.example.currencyconverter.util.DialogUtils

class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners(view)
    }

    private fun initListeners(view: View) {
        val btnCalculate = view.findViewById<Button>(R.id.btn_calculate)
        btnCalculate?.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToConverterFragment()
            findNavController().navigate(action)
        }

        val btnTargetCurrency = view.findViewById<Button>(R.id.btn_target_currency)
        btnTargetCurrency.setOnClickListener {
            val picker = DialogUtils.showCurrencyPicker(
                childFragmentManager,
                btnTargetCurrency.text.toString()
            )
            picker.setOnCurrencyChangeListener { currency ->
                btnTargetCurrency.text = currency
            }
        }

        val btnCurrentCurrency = view.findViewById<Button>(R.id.btn_current_currency)
        btnCurrentCurrency.setOnClickListener {
            val picker = DialogUtils.showCurrencyPicker(
                childFragmentManager,
                btnCurrentCurrency.text.toString()
            )
            picker.setOnCurrencyChangeListener { currency ->
                btnCurrentCurrency.text = currency
            }
        }
    }
}