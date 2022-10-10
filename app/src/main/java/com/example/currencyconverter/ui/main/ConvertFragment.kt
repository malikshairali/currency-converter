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

class ConvertFragment : Fragment() {
    private val args: ConvertFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_convert, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews(view)
    }

    private fun setupViews(view: View) {
        val btnConvert = view.findViewById<Button>(R.id.btn_convert)

        btnConvert?.setOnClickListener {
            val action = ConvertFragmentDirections.actionConverterFragmentToApprovalDialog(
                currentValue = args.currentValue,
                targetValue = args.targetValue,
                conversionRate = args.conversionRate
            )
            findNavController().navigate(action)
        }

        val tvCurrentValue = view.findViewById<TextView>(R.id.tv_current_value)
        tvCurrentValue.text = args.currentValue

        val tvTargetValue = view.findViewById<TextView>(R.id.tv_target_value)
        tvTargetValue.text = args.targetValue
    }
}