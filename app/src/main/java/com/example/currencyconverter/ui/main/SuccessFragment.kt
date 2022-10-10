package com.example.currencyconverter.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.currencyconverter.R

class SuccessFragment : Fragment() {

    private val args: SuccessFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners(view)
    }

    private fun initListeners(view: View) {
        val btnStartOver = view.findViewById<Button>(R.id.btn_start_over)

        btnStartOver?.setOnClickListener {
            val action = SuccessFragmentDirections.actionSuccessFragmentToMainFragment()
            findNavController().navigate(action)
        }

        val tvAccountBalance = view.findViewById<TextView>(R.id.tv_account_balance)
        tvAccountBalance.text = getString(R.string.amount_in_your_account, args.targetValue)

        val tvConversionRate = view.findViewById<TextView>(R.id.tv_conversion_rate)
        tvConversionRate.text = getString(R.string.conversion_rate, args.conversionRate)
    }
}